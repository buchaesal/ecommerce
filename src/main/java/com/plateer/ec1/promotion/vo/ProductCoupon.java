package com.plateer.ec1.promotion.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ProductCoupon {

    private Product product;
    private List<Promotion> promotionList;

    public ProductCoupon(Product product,
                         List<Promotion> promotionList,
                         Map<Long, MaxBenefit> maxBenefitMap){

        this.product = product;
        this.promotionList = promotionList;

        if(!this.promotionList.isEmpty()){

            // 혜택 적용
            applyBenefit();

            // 정책기준 정렬
            this.promotionList = promotionList.stream().sorted(makeComparator()).collect(Collectors.toList());

            // 최대혜택YN을 위한...... map 설정
            manipulateMaxBenefitMap(maxBenefitMap);

            // 기적용여부 맨 앞
            this.promotionList = this.promotionList.stream().sorted(Comparator.comparing(Promotion::getApplyYn).reversed()).collect(Collectors.toList());

        }

    }

    private void manipulateMaxBenefitMap(Map<Long, MaxBenefit> maxBenefitMap){

        Promotion maxPromotion = this.promotionList.get(0);
        MaxBenefit target = maxBenefitMap.get(maxPromotion.getCpnIssNo());

        // target이 null이 아니라는 것은, 이미 다른 상품에서 최대혜택프로모션으로 선택되었다는것을 뜻함.
        if(Objects.nonNull(target)){

            Promotion targetPromotion = target.getPromotion();

            if(maxPromotion.getBenefitPrice() > targetPromotion.getBenefitPrice()){
                // 혜택가가 더 크다면 map에 덮어씌운다.
                coverMapMaxBenefit(maxBenefitMap, maxPromotion);

            }else if(maxPromotion.getBenefitPrice() == targetPromotion.getBenefitPrice()){

                // 같을경우 나머지 순위(상품번호, 단품번호)로 비교
                if(product.getProductNo().compareTo(target.getProduct().getProductNo()) < 0
                || product.getProductItemNo().compareTo(target.getProduct().getProductItemNo()) < 0){
                    coverMapMaxBenefit(maxBenefitMap, maxPromotion);
                }
            }

        }else{
            // null이라면 최초이므로 넣어줌
            coverMapMaxBenefit(maxBenefitMap, maxPromotion);

        }
    }

    private void coverMapMaxBenefit(Map<Long, MaxBenefit> maxBenefitMap, Promotion maxPromotion){
        maxBenefitMap.put(maxPromotion.getCpnIssNo(), MaxBenefit.builder()
                .product(product)
                .promotion(maxPromotion)
                .build());
    }

    private void applyBenefit(){
        promotionList.forEach(promotion -> promotion.setBenefitPrice(product.getProductAmt()));
    }

    private Comparator<Promotion> makeComparator(){

        Comparator<Promotion> compareBenefit = Comparator.comparing(Promotion::getBenefitPrice).reversed();
        Comparator<Promotion> compareProductNo = Comparator.comparing(Promotion::getPrmNo);
        Comparator<Promotion> compareCpnIssNo = Comparator.comparing(Promotion::getCpnIssNo);

        return compareBenefit.thenComparing(compareProductNo).thenComparing(compareCpnIssNo);
    }

}
