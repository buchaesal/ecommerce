package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PayService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;

@Slf4j
@SpringBootTest
class InicisValidationTest {

    @Autowired
    PayService payService;

    OrderInfo orderInfo;
    PayInfo payInfo;

    @BeforeEach
    void init(){

        orderInfo = new OrderInfo("O1", "맥북", "최단비", "eks4116@gmail.com");
        payInfo = new PayInfo(100L, PaymentType.INICIS, "03", "최단비");

    }

    @Test
    void validationTest1(){

        orderInfo = null;

        // orderInfo null
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            payService.approve(new PaymentRequest(orderInfo, Arrays.asList(payInfo)));
        });

    }

    @Test
    void validationTest2(){
        // payInfo null
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            payService.approve(new PaymentRequest(orderInfo, null));
        });
    }

    @Test
    void validationTest3(){
        orderInfo.setOrdNo(null);
        // 주문번호 null
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            payService.approve(new PaymentRequest(orderInfo, Arrays.asList(payInfo)));
        });

    }

    @Test
    void validationTest4(){
        payInfo.setPayAmount(null);
        // 결제금액 null
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            payService.approve(new PaymentRequest(orderInfo, Arrays.asList(payInfo)));
        });
    }

    @Test
    void validationTest5(){
        payInfo.setPaymentType(null);

        // 결제타입 null
        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            payService.approve(new PaymentRequest(orderInfo, Arrays.asList(payInfo)));
        });

    }

//    @Test
//    void validationTest6(){
//
//    }
//


}