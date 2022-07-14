package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.InicisVirtualAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InicisApproveTest {

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
