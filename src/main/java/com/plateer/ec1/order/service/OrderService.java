package com.plateer.ec1.order.service;

import com.plateer.ec1.order.context.OrderContext;
import com.plateer.ec1.order.factory.StrategyFactory;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderContext orderContext;
    private final StrategyFactory strategyFactory;

    public void order(OrderRequest orderRequest){
        log.info("--------주문결제 시작-------- orderRequest : {}", orderRequest);
        orderContext.execute(getDataStrategy(orderRequest), getAfterStrategy(orderRequest), orderRequest);
        log.info("--------주문결제 끝--------");
    }

    private DataStrategy getDataStrategy(OrderRequest orderRequest){
        return strategyFactory.getDataStrategy(orderRequest.getOrderType());
    }

    private AfterStrategy getAfterStrategy(OrderRequest orderRequest){
        return strategyFactory.getAfterStrategy(orderRequest.getSystemType());
    }

}
