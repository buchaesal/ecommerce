package com.plateer.ec1.payment.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderPayment {
    private PaymentMethod paymentMethod;
    private Order order;
}
