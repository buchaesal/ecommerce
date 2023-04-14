package com.plateer.ec1.payment.vo.api;

import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.utils.PaymentUtil;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.PaymentMethod;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResultBase {

    private String ablePartialCancelYn;

    public OpPayInfoModel makeApproveInsertModel(Order order, PaymentMethod paymentMethod){

        return OpPayInfoModel.builder().payNo(PaymentUtil.getNewPayNo())
                .ordNo(order.getOrdNo())
                .payMnCd(paymentMethod.getPaymentType().getPayMnCd())
                .payCcd(OPT0010.APPROVE.code)
                .payPrgsScd(paymentMethod.getPayPrgsScdApprove().code)
                .payAmt(paymentMethod.getPayAmount())
                .rfndAvlAmt(paymentMethod.getPayAmount())
                .build();
    }

    public OpPayInfoModel makeCancelInsertModel(CancelRequest request){
        return null;
    }

}
