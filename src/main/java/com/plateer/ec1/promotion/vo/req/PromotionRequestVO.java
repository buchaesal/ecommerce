package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PromotionRequestVO {
    private String memberNo;
    private List<Product> productList;
    private List<Long> couponIssueNoList;
    private PromotionType promotionType;
}
