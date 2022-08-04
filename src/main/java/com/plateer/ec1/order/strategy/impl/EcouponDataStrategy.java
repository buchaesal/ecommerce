package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.mapper.OrderDao;
import com.plateer.ec1.order.strategy.DataStrategy;
import org.springframework.stereotype.Component;

@Component
public class EcouponDataStrategy extends DataStrategy {

    public EcouponDataStrategy(OrderDao orderDao) {
        super(orderDao);
    }

    @Override
    public OrderType getType() {
        return OrderType.ECOUPON;
    }

}
