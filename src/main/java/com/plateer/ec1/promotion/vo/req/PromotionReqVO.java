package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PromotionReqVO {
    private String memberNo;
    private List<Product> productList;
    private List<Long> couponIssueNoList;
    private PromotionType promotionType;
}
