package com.plateer.ec1.order.service;

import com.plateer.ec1.order.context.OrderContext;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.repository.OrderRepository;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.strategy.impl.BoAfterStrategy;
import com.plateer.ec1.order.strategy.impl.EcouponDataStrategy;
import com.plateer.ec1.order.strategy.impl.FoAfterStrategy;
import com.plateer.ec1.order.strategy.impl.GeneralDataStrategy;
import com.plateer.ec1.payment.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderHistoryService orderHistoryService;
    private final OrderRepository orderRepository;
    private final PayService payService;

    public void order(OrderRequest orderRequest){
        log.info("--------주문결제 시작-------- orderRequest : {}", orderRequest);
        OrderContext orderContext = new OrderContext(orderHistoryService, payService, orderRepository);
        orderContext.execute(getDataStrategy(orderRequest), getAfterStrategy(orderRequest), orderRequest);
        log.info("--------주문결제 끝--------");
    }

    private DataStrategy getDataStrategy(OrderRequest orderRequest){

        if (OrderType.GENERAL == orderRequest.getOrderType()){
            return new GeneralDataStrategy();
        }else if (OrderType.ECOUPON == orderRequest.getOrderType()){
            return new EcouponDataStrategy();
        }else{
            throw new IllegalArgumentException("no dataStrategy");
        }

    }

    private AfterStrategy getAfterStrategy(OrderRequest orderRequest){

        if(OrderSystemType.FO == orderRequest.getSystemType()) {
            return new FoAfterStrategy();
        } else if (OrderSystemType.BO == orderRequest.getSystemType()) {
            return new BoAfterStrategy();
        }else{
            throw new IllegalArgumentException("no AfterStrategy");
        }

    }

}
