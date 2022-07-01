package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0003;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Promotion {

    private Long prmNo;
    private String dcCcd; // 할인구분코드
    private double dcVal; // 할인구분코드에 따른 value

    private long benefitPrice; // 할인금액
    private String maxBenefitYn = "N";

    private long minPurAmt; // 최소구매금액
    private long maxDcAmt; // 최대할인금액

    private List<Product> productList;
    private boolean isValid;

    public void setBenefitPrice(long originalPrice){

        if(PRM0003.PRICE.code.equals(dcCcd)){
            benefitPrice = (long) dcVal;
        }else if(PRM0003.PERCENT.code.equals(dcCcd)){
            benefitPrice = (long) (originalPrice * (dcVal / 100));
        }

        // 최대할인금액 고려
        if(benefitPrice > maxDcAmt) {
            benefitPrice = maxDcAmt;
        }

    }

    private boolean validateMinPurAmt(long purAmt){
        return minPurAmt <= purAmt;
    }

    public void validateCartCoupon(List<Product> productList){

        // 장바구니 상품 총합 계산 (가격*수량)
        long cartSum = productList.stream()
                .map(product -> product.getProductAmt() * product.getProductCnt())
                .mapToLong(i -> i).sum();

        // 최소구매금액 검증된 프로모션만 valid true, 혜택가 set
        if(validateMinPurAmt(cartSum)){
            setBenefitPrice(cartSum);
            this.isValid = true;
        }

    }

}
