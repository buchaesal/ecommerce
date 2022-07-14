package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PayService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.InicisVirtualAccount;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
class InicisTest {

    @Autowired
    PayService payService;
    @Autowired
    Inicis inicis;
    OrderInfo orderInfo;
    PayInfo payInfo;

    @BeforeEach
    void init(){

        orderInfo = new OrderInfo("O1", "맥북", "최단비", "eks4116@gmail.com");
        payInfo = new PayInfo(100L, PaymentType.INICIS, "03", "최단비");

    }

    @Test
    void virtualAccountIssue(){

        InicisVirtualAccount response = inicis.approve(orderInfo, payInfo);
        Assertions.assertNotNull(response.getTid());
        inicis.savePaymentData(orderInfo, payInfo, response);

    }

}