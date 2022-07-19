package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PayServiceTestCancel {

    @Autowired
    PayService payService;
    PaymentCancelRequest cancelRequest;

    @BeforeEach
    void init(){
        cancelRequest = new PaymentCancelRequest("O2", "C1", 100L);
    }

    @Test
    void test1(){
        payService.manipulateAmount(cancelRequest);
        payService.cancel(cancelRequest);
    }

}