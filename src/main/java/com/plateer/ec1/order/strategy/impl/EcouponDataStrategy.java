package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.order.vo.OrderVO;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.strategy.DataStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class EcouponDataStrategy implements DataStrategy {

    @Override
    public OrderVO create(OrderRequest orderRequest, List<OrderProductView> viewList) {
        log.info("모바일쿠폰 주문 데이터 create - orderRequest : {}, viewList : {}", orderRequest, viewList);
        return null;
    }

}
