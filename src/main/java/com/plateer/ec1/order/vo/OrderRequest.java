package com.plateer.ec1.order.vo;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderRequest {

    @NotEmpty
    private String ordNo;
    @NotNull
    private OrderType orderType;
    @NotNull
    private OrderSystemType systemType;
    private @Valid OrderBase orderBase;
    @NotEmpty
    private List<@Valid OrderProduct> productList;
    private List<OrderBenefit> orderBenefitList; // 주문혜택정보
    @NotEmpty
    private List<@Valid OrderDelivery> deliveryList;
    private PaymentRequest paymentRequest;

    public boolean hasPayProcess(){
        return Objects.nonNull(paymentRequest) && !CollectionUtils.isEmpty(paymentRequest.getPayInfoList());
    }

    public long getPayAmount(){
        return hasPayProcess() ? paymentRequest.getPayInfoList().stream().mapToLong(payInfo -> payInfo.getPayAmount()).sum() : 0;
    }

    public List<PaymentType> getPaymentTypeList(){
        return hasPayProcess() ? paymentRequest.getPayInfoList().stream().map(payInfo -> payInfo.getPaymentType()).collect(Collectors.toList()) : Collections.EMPTY_LIST;
    }

}
