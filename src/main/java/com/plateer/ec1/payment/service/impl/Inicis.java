package com.plateer.ec1.payment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.InicisFactory;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.service.InicisApi;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.validation.VirtualAccount;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.InicisVirtualAccount;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;

import java.util.Map;


@Validated
@Component
@RequiredArgsConstructor
public class Inicis extends PaymentService<InicisVirtualAccount> {

    private final InicisApi inicisApi;
    private final PaymentTrxMapper paymentTrxMapper;

    public PaymentType getType(){
        return PaymentType.INICIS;
    }

    public void validateAuth(PayInfo payInfo) {
    }


    @Validated(VirtualAccount.class)
    public InicisVirtualAccount approve(OrderInfo orderInfo, PayInfo payInfo) {

        MultiValueMap<String, String> requestMap = InicisFactory.inicisVirtualAccountRequest(orderInfo, payInfo);
        Map<String, String> apiResult = inicisApi.requestVirtualAccount(requestMap);
        InicisVirtualAccount result = new ObjectMapper().convertValue(apiResult, InicisVirtualAccount.class);
        result.validateApprove();

        return result;
    }


    public void savePaymentData(OrderInfo orderInfo, PayInfo payInfo, InicisVirtualAccount result) {
        OpPayInfoModel model = result.makeApproveInsertModel(orderInfo, payInfo);
        paymentTrxMapper.insertOrderPayment(model);
    }

    public void cancelPay(OriginalOrder originalOrder) {
    }

    public void netCancel(NetCancelReqVO netCancelReqVO) {
    }

}
