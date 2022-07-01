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
    private boolean isValid;

    public CouponProduct(Promotion promotion, List<Product> productList){
        this.promotion = promotion;
        this.productList = productList;
    }

    public void validateCartCoupon(List<Product> productList){

        // 장바구니 상품 총합 계산 (가격*수량)
        long cartSum = productList.stream()
                .map(product -> product.getProductAmt() * product.getProductCnt())
                .mapToLong(i -> i).sum();

        // 최소구매금액 검증된 프로모션만 valid true, 혜택가 set
        if(this.promotion.validateMinPurAmt(cartSum)){
            this.promotion.setBenefitPrice(cartSum);
            this.isValid = true;
        }

    }

}
