package com.plateer.ec1.order.service;

import com.plateer.ec1.order.context.OrderContext;
import com.plateer.ec1.order.factory.StrategyFactory;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class OrderService {

    private final OrderContext orderContext;
    private final StrategyFactory strategyFactory;

    @Validated
    public void order(@Valid OrderRequest orderRequest){
        orderContext.execute(getDataStrategy(orderRequest), getAfterStrategy(orderRequest), orderRequest);
    }

    private DataStrategy getDataStrategy(OrderRequest orderRequest){
        return strategyFactory.getDataStrategy(orderRequest.getOrderType());
    }

    private AfterStrategy getAfterStrategy(OrderRequest orderRequest){
        return strategyFactory.getAfterStrategy(orderRequest.getSystemType());
    }

}
