package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
@Component
public class PaymentServiceFactory {

    private final Map<PaymentType, PaymentService> map = new HashMap<>();

    public PaymentServiceFactory(List<PaymentService> serviceList){
      serviceList.forEach(e -> map.put(e.getType(), e));
    }

    public PaymentService getPaymentService(PaymentType type){
        try {
            return map.get(type);
        }catch (NullPointerException e){
            throw new IllegalArgumentException("해당하는 결제유형이 없습니다!");
        }
    }
}
