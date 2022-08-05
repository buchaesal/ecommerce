package com.plateer.ec1.order.service;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.vo.OrderBase;
import com.plateer.ec1.order.vo.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class OrderServiceTest {

    OrderRequest orderRequest;
    @Autowired
    OrderService orderService;

    @BeforeEach
    void init(){
        String ordNo = "O"+LocalDateTime.now();
        orderRequest = new OrderRequest();
        orderRequest.setOrdNo(ordNo);
        orderRequest.setOrderType(OrderType.GENERAL);
        orderRequest.setSystemType(OrderSystemType.FO);
        orderRequest.setOrderBase(new OrderBase(ordNo,
                "test01",
                "10",
                "10",
                "최단비",
                "01047264128",
                "강서구",
                "아파트",
                "",
                "",
                ""));
    }

    @Test
    void logTest(){
        orderService.order(orderRequest);
    }

}