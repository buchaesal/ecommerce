package com.plateer.ec1.payment.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plateer.ec1.common.code.order.OPT0009;
import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.utils.PaymentUtil;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PaymentMethod;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

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
  public OpPayInfoModel makeApproveInsertModel(Order order, PaymentMethod paymentMethod){
        return OpPayInfoModel.builder()
                .payNo(PaymentUtil.getNewPayNo())
                .ordNo(order.getOrdNo())
                .payMnCd(OPT0009.VIRTUAL_ACCOUNT.code)
                .payCcd(OPT0010.APPROVE.code)
                .payPrgsScd(OPT0011.REQUEST_APPROVE.code)
                .payAmt(paymentMethod.getPayAmount())
                .cnclAmt(0L)
                .rfndAvlAmt(0L)
                .trsnId(tid)
                .vrValDt(validDate)
                .vrValTt(validTime)
                .vrAcct(vacct)
                .vrAcctNm(paymentMethod.getDepositorName())
                .vrBnkCd(paymentMethod.getBankCode())
                .build();
  }

  @Override
  public OpPayInfoModel makeCancelInsertModel(CancelRequest request){
        OriginalOrder originalOrder = request.getOriginalOrder();
        return OpPayInfoModel.builder()
                .payNo(PaymentUtil.getNewPayNo())
                .ordNo(request.getOrdNo())
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
