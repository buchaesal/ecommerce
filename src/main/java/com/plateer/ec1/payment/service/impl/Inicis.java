package com.plateer.ec1.payment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.InicisFactory;
import com.plateer.ec1.payment.service.InicisApi;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.InicisVirtualAccount;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;


@Component
@RequiredArgsConstructor
public class Inicis extends PaymentService<InicisVirtualAccount> {

    private final InicisApi inicisApi;

    public PaymentType getType(){
        return PaymentType.INICIS;
    }

    public void validateAuth(PayInfo payInfo) {
    }


    public InicisVirtualAccount approve(OrderInfo orderInfo, PayInfo payInfo) {

        MultiValueMap<String, String> requestMap = InicisFactory.inicisVirtualAccountRequest(orderInfo, payInfo);
        Map<String, String> apiResult = inicisApi.requestVirtualAccount(requestMap);
        InicisVirtualAccount result = new ObjectMapper().convertValue(apiResult, InicisVirtualAccount.class);
        result.validateApprove();

        return result;
    }


    public void savePaymentData(OrderInfo orderInfo, PayInfo payInfo, InicisVirtualAccount result) {
        OpPayInfoModel model = result.makeInsertModel(orderInfo, payInfo);
    }

    public void cancelPay(OriginalOrder originalOrder) {
    }

    public void netCancel(NetCancelReqVO netCancelReqVO) {
    }

}
