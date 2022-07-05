package com.plateer.ec1.promotion.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductCoupon {

    private Product product;
    private List<Promotion> promotionList;

    public void applyBenefit(){

        if(!promotionList.isEmpty()){
            // 혜택 적용
            promotionList.forEach(promotion -> promotion.setBenefitPrice(product.getProductAmt()));
            promotionList = promotionList.stream().sorted(makeComparator()).collect(Collectors.toList());
            promotionList.get(0).setMaxBenefitYn("Y");
            // 기적용여부 맨 앞
            promotionList = promotionList.stream().sorted(Comparator.comparing(Promotion::getApplyYn).reversed()).collect(Collectors.toList());

        }

    }

    private Comparator<Promotion> makeComparator(){

        Comparator<Promotion> compareBenefit = Comparator.comparing(Promotion::getBenefitPrice).reversed();
        Comparator<Promotion> compareProductNo = Comparator.comparing(Promotion::getPrmNo);
        Comparator<Promotion> compareCpnIssNo = Comparator.comparing(Promotion::getCpnIssNo);

        return compareBenefit.thenComparing(compareProductNo).thenComparing(compareCpnIssNo);
    }

}
