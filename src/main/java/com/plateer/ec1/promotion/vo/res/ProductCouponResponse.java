package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.MaxBenefit;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.ProductCoupon;
import com.plateer.ec1.promotion.vo.Promotion;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class ProductCouponResponse extends BaseResponseVO {

    private List<ProductCoupon> productCouponList;

    public ProductCouponResponse(List<ProductCoupon> productCouponList, Map<Long, MaxBenefit> maxBenefitMap){

        // 최종리턴전, 최대혜택YN을 set해줘야한다.
        Iterator<Long> iterator = maxBenefitMap.keySet().iterator();

        while(iterator.hasNext()){
            Long key = iterator.next();
            MaxBenefit maxBenefit = maxBenefitMap.get(key);
            Product maxBenefitProduct = maxBenefit.getProduct();
            Promotion maxBenefitPromotion = maxBenefit.getPromotion();

            productCouponList.forEach((productCoupon) -> {
                Product product = productCoupon.getProduct();
                if(product.getProductNo().equals(maxBenefitProduct.getProductNo())
                && product.getProductItemNo().equals(maxBenefitProduct.getProductItemNo())){
                    productCoupon.getPromotionList().stream()
                            .filter((promotion) -> promotion.getCpnIssNo() == key)
                            .findFirst()
                            .get()
                            .setMaxBenefitYn("Y");
                }
            });

            this.productCouponList = productCouponList;
        }

    }

}
