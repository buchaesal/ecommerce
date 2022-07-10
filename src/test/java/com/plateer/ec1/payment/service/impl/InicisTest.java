package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InicisTest {

    @Autowired
    Inicis inicis;

    @Test
    void approve(){

        OrderInfo orderInfo = new OrderInfo("O1", "맥북", "최단비", "eks4116@gmail.com");
        PayInfo payInfo = new PayInfo(100L, "03", PaymentType.INICIS, "최단비");

        inicis.approvePay(orderInfo, payInfo);
    }

}