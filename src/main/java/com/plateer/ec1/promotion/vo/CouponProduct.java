package com.plateer.ec1.promotion.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CouponProduct {

    private Promotion promotion;
    private List<Product> productList;

    public CouponProduct(Promotion promotion){
        productList = promotion.getProductList();
    }

}
