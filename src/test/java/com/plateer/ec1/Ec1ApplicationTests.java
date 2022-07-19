package com.plateer.ec1;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.service.OrderService;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ec1ApplicationTests {

    @Autowired
    PaymentMapper paymentMapper;
    @Autowired
    OrderService orderService;

    @Test
    void orderTest(){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderType(OrderType.GENERAL);
        orderRequest.setSystemType(OrderSystemType.BO);

        orderService.order(orderRequest);
    }

}
