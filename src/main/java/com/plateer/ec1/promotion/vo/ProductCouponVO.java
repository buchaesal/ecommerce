package com.plateer.ec1.promotion.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class ProductCouponVO {
    private Product product;
    private List<Promotion> promotionList;
}
