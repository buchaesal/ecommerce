package com.plateer.ec1.payment.mapper;

import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface PaymentMapper {

    OriginalOrder getOriginalOrder(String ordNo);

}
