package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.PaymentResultBase;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Point extends PaymentService<PaymentResultBase> {

    public PaymentType getType(){
        return PaymentType.POINT;
    }

    public void validateAuth(PayInfo payInfo) {
    }

    @Override
    public PaymentResultBase approve(OrderInfo orderInfo, PayInfo payInfo) {
        return null;
    }

    public void savePaymentData(OrderInfo orderInfo, PayInfo payInfo, PaymentResultBase result) {
    }


    public void cancelPay(OriginalOrder originalOrder) {
        log.info("포인트 결제 취소 - payInfo : {}", originalOrder);
    }

    public void netCancel(NetCancelReqVO netCancelReqVO) {
        // 포인트결제 망취소 X
    }

}
