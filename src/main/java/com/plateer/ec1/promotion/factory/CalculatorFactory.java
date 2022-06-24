package com.plateer.ec1.promotion.factory;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalculatorFactory {

    private final Map<PromotionType, Calculator> map = new HashMap<>();

    public CalculatorFactory(List<Calculator> list){
        list.forEach(e -> map.put(e.getType(), e));
    }

    public Calculator getPromotionCalculationData(PromotionType type){
        try {
            return map.get(type);
        }catch (NullPointerException e){
            throw new IllegalArgumentException("해당하는 프로모션 유형이 없습니다!");
        }
    }

}
