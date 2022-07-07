package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.MaxBenefit;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.ProductCoupon;
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

        // 최대혜택프로모션 정보가 들어있는 Map (쿠폰발급번호, 프로모션정보) 을 하나씩 꺼내서,
        // 해당 상품프로모션을 찾아 최대혜택값여부 YN을 세팅해준다.
        while(iterator.hasNext()){
            Long key = iterator.next();
            MaxBenefit maxBenefit = maxBenefitMap.get(key);
            Product maxBenefitProduct = maxBenefit.getProduct();

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
