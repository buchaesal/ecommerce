package com.plateer.ec1.order.vo;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    private OrderType orderType;
    @NotNull
    private OrderSystemType systemType;
    @NotEmpty
    private String ordNo;
    private @Valid OrderBase orderBase;
    @NotEmpty
    private List<@Valid OrderProduct> productList;
    private List<OrderBenefit> orderBenefitList; // 주문혜택정보
    @NotEmpty
    private List<@Valid OrderDelivery> deliveryList;
    private PaymentRequest paymentRequest;

}
