package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PromotionRequest {
    private String mbrNo;
    private List<Product> productList;
    private List<Long> couponIssueNoList;
    private PromotionType promotionType;
}
