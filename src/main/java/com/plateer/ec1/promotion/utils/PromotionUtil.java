package com.plateer.ec1.promotion.utils;

import com.plateer.ec1.promotion.vo.Promotion;

import java.util.Comparator;
import java.util.List;

public class PromotionUtil {

    public static void setMaxBenefitYn(List<Promotion> list){
        // 최대혜택 프로모션에 maxBenefitYn set
        list.stream()
                .max(Comparator.comparing(Promotion::getBenefitPrice))
                .get()
                .setMaxBenefitYn("Y");
    }

}
