package com.plateer.ec1.order.strategy;

import com.plateer.ec1.order.vo.OrderVO;
import com.plateer.ec1.order.vo.OrderRequest;

public interface AfterStrategy {
    void call(OrderRequest orderRequest, OrderVO orderVO);
}
