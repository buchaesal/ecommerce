package com.plateer.ec1.payment.vo.api;

import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.utils.PaymentUtil;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResultBase {

    private String ablePartialCancelYn;

    public OpPayInfoModel makeApproveInsertModel(OrderInfo orderInfo, PayInfo payInfo){

        return OpPayInfoModel.builder().payNo(PaymentUtil.getNewPayNo())
                .ordNo(orderInfo.getOrdNo())
                .payMnCd(payInfo.getPaymentType().getPayMnCd())
                .payCcd(OPT0010.APPROVE.code)
                .payPrgsScd(payInfo.getPayPrgsScdApprove().code)
                .payAmt(payInfo.getPayAmount())
                .rfndAvlAmt(payInfo.getPayAmount())
                .build();
    }

    public OpPayInfoModel makeCancelInsertModel(PaymentCancelRequest request, OriginalOrder originalOrder){
        return null;
    }

}
