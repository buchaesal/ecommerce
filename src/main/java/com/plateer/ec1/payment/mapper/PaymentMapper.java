package com.plateer.ec1.payment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface PaymentMapper {
    @Transactional
    String test();
}
