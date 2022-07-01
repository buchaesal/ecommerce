package com.plateer.ec1.promotion.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductCoupon {

    private Product product;
    private List<Promotion> promotionList;

    public void applyBenefit(){

        if(!this.promotionList.isEmpty()){
            // 혜택 적용
            this.promotionList.forEach(promotion -> promotion.setBenefitPrice(product.getProductAmt()));
            this.promotionList.stream()
                    .max(Comparator.comparing(Promotion::getBenefitPrice))
                    .get()
                    .setMaxBenefitYn("Y");
        }

    }

}
