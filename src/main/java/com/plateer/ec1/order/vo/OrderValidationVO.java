package com.plateer.ec1.order.vo;

import java.util.List;

public class OrderValidationVO {
    private OrderRequest request;
    private List<OrderProductView> product;

    public OrderValidationVO(OrderRequest request, List<OrderProductView> product) {
        this.request = request;
        this.product = product;
    }
}
