package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PayService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InicisTest {

    @Autowired
    PayService payService;

    @Test
    void approve(){

        OrderInfo orderInfo = new OrderInfo("O1", "맥북", "최단비", "eks4116@gmail.com");
        PayInfo payInfo = new PayInfo(100L, "03", PaymentType.INICIS, "최단비");

        List<PayApproveResponse> response = payService.approve(new PaymentRequest(orderInfo, Arrays.asList(payInfo)));
    }

}