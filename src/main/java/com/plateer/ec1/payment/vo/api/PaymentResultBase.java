package com.plateer.ec1.payment.vo.api;

import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.utils.PaymentUtil;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.OrderPayment;
import com.plateer.ec1.payment.vo.Payment;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResultBase {

    private String ablePartialCancelYn;

    public OpPayInfoModel makeApproveInsertModel(OrderPayment orderPayment){
        Order order = orderPayment.getOrder();
        Payment payment = orderPayment.getPayment();
        return OpPayInfoModel.builder().payNo(PaymentUtil.getNewPayNo())
                .ordNo(order.getOrdNo())
                .payMnCd(payment.getPaymentType().getPayMnCd())
                .payCcd(OPT0010.APPROVE.code)
                .payPrgsScd(payment.getPayPrgsScdApprove().code)
                .payAmt(payment.getPayAmount())
                .rfndAvlAmt(payment.getPayAmount())
                .build();
    }

    public OpPayInfoModel makeCancelInsertModel(CancelRequest request){
        return null;
    }

}
