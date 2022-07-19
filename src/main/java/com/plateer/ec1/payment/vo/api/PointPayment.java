package com.plateer.ec1.payment.vo.api;

import com.plateer.ec1.common.code.order.OPT0009;
import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PointPayment extends PaymentResultBase{

    @Override
    public OpPayInfoModel makeApproveInsertModel(OrderInfo orderInfo, PayInfo payInfo){
        return OpPayInfoModel.builder()
                .ordNo(orderInfo.getOrdNo())
                .payMnCd(OPT0009.POINT.code)
                .payCcd(OPT0010.APPROVE.code)
                .payPrgsScd(OPT0011.COMPLETE_APPROVE.code)
                .payAmt(payInfo.getPayAmount())
                .rfndAvlAmt(payInfo.getPayAmount())
                .payCmtDtime(LocalDateTime.now())
                .build();
    }

    @Override
    public OpPayInfoModel makeCancelInsertModel(PaymentCancelRequest request, OriginalOrder originalOrder){
        return OpPayInfoModel.builder()
                .ordNo(request.getOrrNo())
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
