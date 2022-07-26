package com.plateer.ec1.order.service;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.vo.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    OrderRequest orderRequest;
    @Autowired
    OrderService orderService;

    @BeforeEach
    void init(){
        orderRequest = new OrderRequest();
        orderRequest.setOrdNo("O1");
        orderRequest.setOrderType(OrderType.GENERAL);
        orderRequest.setSystemType(OrderSystemType.FO);
    }

    @Test
    void logTest(){
        orderService.order(orderRequest);
    }

}