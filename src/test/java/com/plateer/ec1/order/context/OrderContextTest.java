package com.plateer.ec1.order.context;

import com.plateer.ec1.order.vo.OrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderContextTest {

    OrderRequest orderRequest;
    @Autowired
    OrderContext orderContext;

    @BeforeEach
    void init(){
        orderRequest = new OrderRequest();
        orderRequest.setOrdNo("O1");
    }

    void logTest(){

    }

}