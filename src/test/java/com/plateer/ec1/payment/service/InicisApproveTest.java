package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.strategy.impl.Inicis;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.Payment;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InicisApproveTest {

    @Autowired
    Inicis inicis;
    @Autowired
    PaymentDataService dataService;
    Order order;
    Payment payment;
    PaymentRequest paymentRequest;
    @Autowired
    PaymentService paymentService;

    @BeforeEach
    void init(){

        order = Order.builder().ordNo("01").goodsNm("맥북").ordNm("최단비").ordEmail("eks4116@gmail.com").build();
        List<Payment> list = new ArrayList<>();
        list.add(Payment.builder().payAmount(100L).paymentType(PaymentType.INICIS).build());
        paymentRequest = PaymentRequest.builder().order(order).paymentList(list).build();

    }

    @Test
    void approve(){
        paymentService.approve(paymentRequest);
    }

    @Test
    void virtualAccountIssue(){

//        InicisVirtualAccount response = inicis.approve(order, paymentMethod);
//        Assertions.assertNotNull(response.getTid());
//        dataService.saveOrderPaymentData(response.makeApproveInsertModel(order, paymentMethod));
    }

}
