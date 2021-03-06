package com.plateer.ec1.order.strategy;

import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.vo.OrderVO;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderRequest;

import java.util.List;

public interface DataStrategy {
    OrderType getType();
    OrderVO create(OrderRequest orderRequest, List<OrderProductView> viewList);
}
