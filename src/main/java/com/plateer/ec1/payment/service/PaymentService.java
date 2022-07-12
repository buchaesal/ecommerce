package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.PaymentResultBase;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import org.springframework.transaction.annotation.Transactional;

public abstract class PaymentService<T extends PaymentResultBase> {

    abstract public PaymentType getType();
    PayApproveResponse executePayment(OrderInfo orderInfo, PayInfo payInfo){

         // 인증값 검증
        validateAuth(payInfo);

        // 승인요청
        T result = approve(orderInfo, payInfo);

        // 주문결제 데이터 저장
        savePaymentData(orderInfo, payInfo, result);

        return new PayApproveResponse(payInfo.getPaymentType(), result.getAblePartialCancelYn());

    }

    abstract public void validateAuth(PayInfo payInfo);

    abstract public T approve(OrderInfo orderInfo, PayInfo payInfo);

    abstract public void savePaymentData(OrderInfo orderInfo, PayInfo payInfo, T result);

    abstract public void cancelPay(OriginalOrder originalOrder);

    abstract public void netCancel(NetCancelReqVO netCancelReqVO);

}
