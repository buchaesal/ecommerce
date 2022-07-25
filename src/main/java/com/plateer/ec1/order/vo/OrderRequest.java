package com.plateer.ec1.order.vo;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private OrderType orderType;
    private OrderSystemType systemType;
    private String ordNo;
    private OrderBase orderBase;
    private List<OrderProduct> productList;
    private List<OrderBenefit> orderBenefitList; // 주문혜택정보
    private List<OrderDelivery> deliveryList;
    private PaymentRequest paymentRequest;

}
