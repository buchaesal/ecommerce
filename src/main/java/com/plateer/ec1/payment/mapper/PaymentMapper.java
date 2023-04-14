package com.plateer.ec1.payment.mapper;

import com.plateer.ec1.payment.vo.OriginalOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    OriginalOrder getOriginalOrder(String ordNo);

}
