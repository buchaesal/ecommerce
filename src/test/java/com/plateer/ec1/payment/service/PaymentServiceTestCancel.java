package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.vo.req.CancelRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentServiceTestCancel {

    @Autowired
    PaymentService paymentService;
    CancelRequest cancelRequest;

    @BeforeEach
    void init(){
        cancelRequest = new CancelRequest("O2", "C1", 100L, null);
    }

    @Test
    void test1(){
        paymentService.manipulateAmount(cancelRequest);
        paymentService.cancel(cancelRequest);
    }

}