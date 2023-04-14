package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentStrategyFactory {

    private final Map<PaymentType, PaymentStrategy> map = new HashMap<>();

    public PaymentStrategyFactory(List<PaymentStrategy> strategies){
        strategies.forEach(e -> map.put(e.getType(), e));
    }

    public PaymentStrategy getPaymentStrategy(PaymentType type){
        try {
            return map.get(type);
        }catch (NullPointerException e){
            throw new IllegalArgumentException("해당하는 결제유형이 없습니다!");
        }
    }
}
