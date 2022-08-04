package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.mapper.OrderDao;
import com.plateer.ec1.order.strategy.DataStrategy;
import org.springframework.stereotype.Component;

@Component
public class GeneralDataStrategy extends DataStrategy {

    public GeneralDataStrategy(OrderDao orderDao) {
        super(orderDao);
    }

    @Override
    public OrderType getType() {
        return OrderType.GENERAL;
    }

}
