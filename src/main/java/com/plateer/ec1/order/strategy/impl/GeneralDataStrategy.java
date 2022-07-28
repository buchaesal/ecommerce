package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.vo.OrderVO;
import org.springframework.stereotype.Component;

@Component
public class GeneralDataStrategy implements DataStrategy {

    @Override
    public OrderType getType() {
        return OrderType.GENERAL;
    }

    @Override
    public OrderVO create(OrderRequest orderRequest) {
        return null;
    }

}
