package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.vo.OrderVO;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.strategy.AfterStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FoAfterStrategy implements AfterStrategy {

    @Override
    public OrderSystemType getType() {
        return OrderSystemType.FO;
    }

    @Override
    public void call(OrderRequest orderRequest, OrderVO orderVO) {
        log.info("FO 주문 후처리");
    }

}
