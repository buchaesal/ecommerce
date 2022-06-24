package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.vo.OrderVO;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.strategy.DataStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GeneralDataStrategy implements DataStrategy {

    @Override
    public OrderType getType() {
        return OrderType.GENERAL;
    }

    @Override
    public OrderVO create(OrderRequest orderRequest, List<OrderProductView> viewList) {
        log.info("일반 주문 데이터 create - orderRequest : {}, viewList : {}", orderRequest, viewList);
        return null;
    }

}
