package com.plateer.ec1.payment.vo.req;

import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
public class PaymentRequest {

    @NotNull
    @Valid
    Order order;
    @NotNull
    List<@Valid @NotNull PaymentMethod> paymentMethodList;

}
