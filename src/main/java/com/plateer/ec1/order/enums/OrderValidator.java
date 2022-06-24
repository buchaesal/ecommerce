package com.plateer.ec1.order.enums;

import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.vo.OrderValidationVO;
import com.plateer.ec1.order.validator.OrderCommonValidator;
import com.plateer.ec1.order.validator.OrderSystemValidator;
import com.plateer.ec1.order.validator.OrderTypeValidator;

import java.util.Arrays;
import java.util.function.Predicate;

public enum OrderValidator implements Predicate<OrderValidationVO> {

    FO_GENERAL("FO", "general",
            OrderCommonValidator.commonValidator),
    BO_GENERAL("BO", "general",
            OrderCommonValidator.commonValidator
                    .and(OrderSystemValidator.isAbleBoOrder)),
    FO_ECOUPON("FO", "ecoupon",
            OrderCommonValidator.commonValidator
                    .and(OrderTypeValidator.isAbleEcouponOrder)),
    BO_ECOUPON("BO", "ecoupon",
            OrderCommonValidator.commonValidator
                    .and(OrderTypeValidator.isAbleEcouponOrder));

    private String systemCode;
    private String orderType;
    private Predicate predicate;

    OrderValidator(String systemCode, String orderType, Predicate predicate) {
        this.systemCode = systemCode;
        this.orderType = orderType;
        this.predicate = predicate;
    }

    @Override
    public boolean test(OrderValidationVO orderValidationVO){
        return predicate.test(orderValidationVO);
    }

    public static OrderValidator get(OrderRequest orderRequest){
        return Arrays.stream(OrderValidator.values())
                .filter(ele -> ele.orderType.equals(orderRequest.getOrderType()) && ele.systemCode.equals(orderRequest.getSystemType()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 주문유형이 없습니다!"));
    }

}
