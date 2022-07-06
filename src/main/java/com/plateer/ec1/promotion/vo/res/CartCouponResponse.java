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
@AllArgsConstructor
public class CartCouponResponse extends BaseResponseVO {

    private List<CouponProduct> couponProductList;

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
