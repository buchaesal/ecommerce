package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.InicisFactory;
import com.plateer.ec1.payment.service.InicisApi;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;


@Slf4j
@Component
@RequiredArgsConstructor
public class Inicis implements PaymentService {

    private final InicisApi inicisApi;

    public PaymentType getType(){
        return PaymentType.INICIS;
    }

    public void validateAuth(PayInfo payInfo) {
        log.info("이니시스 인증결과 유효성검사");
    }

    @Override
    public PayApproveResponse approvePay(OrderInfo orderInfo, PayInfo payInfo) {

        MultiValueMap<String, String> requestMap = InicisFactory.inicisVirtualAccountRequest(orderInfo, payInfo);
        Map<String, String> apiResult = inicisApi.requestVirtualAccount(requestMap);

        return new PayApproveResponse(PaymentType.INICIS, null);
    }


    private void validatePayment(Map<String, String> apiResult){
        if(!PaymentType.INICIS.getApproveSuccessCode().equals(apiResult.get("resultCode"))){
            throw new RuntimeException("approve rejected");
        }
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
