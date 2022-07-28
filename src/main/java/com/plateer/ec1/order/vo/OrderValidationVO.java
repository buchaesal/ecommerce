package com.plateer.ec1.order.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderValidationVO {

    private OrderRequest orderRequest;
    private List<OrderProductView> productViewList;

}
