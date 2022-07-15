package com.plateer.ec1.payment.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plateer.ec1.common.code.order.OPT0009;
import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.utils.PaymentUtil;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InicisVirtualAccount extends PaymentResultBase {

    private String resultCode;
    private String tid;
    private String vacct; // 가상계좌번호
    private String validDate; // 입금기한일
    private String validTime; // 입금기한시간

    public void validateApprove(){
      if(!PaymentType.INICIS.getApproveSuccessCode().equals(resultCode)){
          throw new RuntimeException("approve rejected");
      }
    }

    public void validateCancel(){
        if(!PaymentType.INICIS.getCancelSuccessCode().equals(resultCode)){
            throw new RuntimeException("cancel rejected");
        }
    }

  @Override
  public OpPayInfoModel makeApproveInsertModel(OrderInfo orderInfo, PayInfo payInfo){
        return OpPayInfoModel.builder()
                .payNo(PaymentUtil.getNewPayNo())
                .ordNo(orderInfo.getOrdNo())
                .payMnCd(OPT0009.VIRTUAL_ACCOUNT.code)
                .payCcd(OPT0010.APPROVE.code)
                .payPrgsScd(OPT0011.REQUEST_APPROVE.code)
                .payAmt(payInfo.getPayAmount())
                .cnclAmt(0L)
                .rfndAvlAmt(0L)
                .trsnId(tid)
                .vrValDt(validDate)
                .vrValTt(validTime)
                .vrAcct(vacct)
                .vrAcctNm(payInfo.getDepositorName())
                .vrBnkCd(payInfo.getBankCode())
                .build();
  }

  @Override
  public OpPayInfoModel makeCancelInsertModel(PaymentCancelRequest request, OriginalOrder originalOrder){
        return OpPayInfoModel.builder()
                .payNo(PaymentUtil.getNewPayNo())
                .ordNo(request.getOrrNo())
                .clmNo(request.getClmNo())
                .payMnCd(originalOrder.getPayMnCd())
                .payCcd(OPT0010.CANCEL.code)
                .payPrgsScd(OPT0011.COMPLETE_REFUND.code)
                .payAmt(request.getCnclAmt())
                .cnclAmt(0L)
                .rfndAvlAmt(0L)
                .orgPayNo(originalOrder.getPayNo())
                .trsnId(StringUtils.hasText(tid) ? tid : originalOrder.getTrsnId())
                .vrAcct(originalOrder.getVrAcct())
                .vrAcctNm(originalOrder.getVrAcctNm())
                .vrBnkCd(originalOrder.getVrBnkCd())
                .vrValDt(originalOrder.getVrValDt())
                .vrValTt(originalOrder.getVrValTt())
                .build();
  }

}
