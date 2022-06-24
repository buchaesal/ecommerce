package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import com.plateer.ec1.payment.vo.res.PayApproveResVO;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class Inicis implements PaymentService {

//    private final PaymentDataManipulator paymentDataManipulator;

    public PaymentType getType(){
        return PaymentType.INICIS;
    }

    public void validateAuth(PayInfo payInfo) {
        log.info("이니시스 인증결과 유효성검사");
    }

    @Override
    public PayApproveResVO approvePay(PayInfo payInfo) {
        log.info("이니시스 결제 승인 - payInfo : {}", payInfo);
        return null;
    }

    @Override
    public void cancelPay(OriginalOrder originalOrder) {
        log.info("이니시스 취소 - OriginalOrder : {}", originalOrder);
    }

    @Override
    public void netCancel(NetCancelReqVO netCancelReqVO) {
        // 이니시스 가상계좌 결제 망취소 X
    }

}
