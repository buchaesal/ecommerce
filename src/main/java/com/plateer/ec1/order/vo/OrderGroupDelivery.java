package com.plateer.ec1.order.vo;

import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderGroupDelivery {

    private Long dvGrpNo;
    private List<Product> productList;

}
