package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.CouponProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartCouponResponse extends BaseResponseVO {

    private List<CouponProduct> couponProductList;

    public CartCouponResponse(List<CouponProduct> couponProductList){

        this.couponProductList = couponProductList;

        // isValid true값들만 filter
        filterValidated();

        // 최대혜택 프로모션 YN set
        setMaxBenefitYn();

    }

    public void filterValidated(){
        couponProductList = couponProductList.stream()
                .filter(CouponProduct::isValid)
                .collect(Collectors.toList());
    }

    public void setMaxBenefitYn(){
        if(couponProductList.size() > 0){
            couponProductList.stream()
                    .max(Comparator.comparing(couponProduct -> couponProduct.getPromotion().getBenefitPrice()))
                    .get()
                    .getPromotion()
                    .setMaxBenefitYn("Y");
        }
    }

}
