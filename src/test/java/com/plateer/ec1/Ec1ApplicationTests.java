package com.plateer.ec1;

import com.plateer.ec1.payment.mapper.PaymentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ec1ApplicationTests {

    @Autowired
    PaymentMapper paymentMapper;

    @Test
    void test(){
        paymentMapper.test();
    }

}
