package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.promotion.utils.PromotionUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductCoupon {

    private Product product;
    private List<Promotion> promotionList;

    public ProductCoupon(Product product){
        this.product = product;
    }

    public void setPromotionListWithBenefit(List<Promotion> promotionList){
        if(!promotionList.isEmpty()){
            // 혜택 적용
            promotionList.forEach(promotion -> promotion.setBenefitPrice(product.getProductAmt()));
            PromotionUtil.setMaxBenefitYn(promotionList);
        }
    }

}
