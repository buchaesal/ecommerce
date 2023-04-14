package com.plateer.ec1.payment.vo.api;

import com.plateer.ec1.common.code.order.OPT0009;
import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.OrderPayment;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.Payment;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PointPayment extends PaymentResultBase {

    @Override
    public OpPayInfoModel makeApproveInsertModel(OrderPayment orderPayment){
        Order order = orderPayment.getOrder();
        Payment payment = orderPayment.getPayment();
        return OpPayInfoModel.builder()
                .ordNo(order.getOrdNo())
                .payMnCd(OPT0009.POINT.code)
                .payCcd(OPT0010.APPROVE.code)
                .payPrgsScd(OPT0011.COMPLETE_APPROVE.code)
                .payAmt(payment.getPayAmount())
                .rfndAvlAmt(payment.getPayAmount())
                .payCmtDtime(LocalDateTime.now())
                .build();
    }

    @Override
    public OpPayInfoModel makeCancelInsertModel(CancelRequest request){
        OriginalOrder originalOrder = request.getOriginalOrder();
        return OpPayInfoModel.builder()
                .ordNo(request.getOrdNo())
                .clmNo(request.getClmNo())
                .payMnCd(OPT0009.POINT.code)
                .payCcd(OPT0010.CANCEL.code)
                .payPrgsScd(OPT0011.COMPLETE_REFUND.code)
                .payAmt(request.getCnclAmt())
                .cnclAmt(0L)
                .rfndAvlAmt(0L)
                .orgPayNo(originalOrder.getPayNo())
                .payCmtDtime(LocalDateTime.now())
                .build();
    }

}
