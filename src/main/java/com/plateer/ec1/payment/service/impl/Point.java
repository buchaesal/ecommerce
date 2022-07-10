package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Point implements PaymentService {

    public PaymentType getType(){
        return PaymentType.POINT;
    }

    @Override
    public void validateAuth(PayInfo payInfo) {
        // 포인트결제 인증값검증 X
    }

    @Override
    public PayApproveResponse approvePay(OrderInfo orderInfo, PayInfo payInfo) {
        log.info("포인트 결제 승인 - payInfo : {}", payInfo);
        return null;
    }

    @Override
    public void cancelPay(OriginalOrder originalOrder) {
        log.info("포인트 결제 취소 - payInfo : {}", originalOrder);
    }

    @Override
    public void netCancel(NetCancelReqVO netCancelReqVO) {
        // 포인트결제 망취소 X
    }
}
