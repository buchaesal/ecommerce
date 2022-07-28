package com.plateer.ec1.order.enums;

import com.plateer.ec1.order.validator.OrderCommonValidator;
import com.plateer.ec1.order.validator.OrderSystemValidator;
import com.plateer.ec1.order.validator.OrderTypeValidator;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.vo.OrderValidationVO;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Predicate;

@RequiredArgsConstructor
public enum OrderValidator implements Predicate<OrderValidationVO> {

    FO_GENERAL(OrderSystemType.FO, OrderType.GENERAL,
            OrderCommonValidator.commonValidator
                    .and(OrderTypeValidator.isAbleGeneralOrder)),
    BO_GENERAL(OrderSystemType.BO, OrderType.GENERAL,
            OrderCommonValidator.commonValidator
                    .and(OrderTypeValidator.isAbleGeneralOrder)
                    .and(OrderSystemValidator.isAbleBoOrder)),
    FO_ECOUPON(OrderSystemType.FO, OrderType.ECOUPON,
            OrderCommonValidator.commonValidator
                    .and(OrderTypeValidator.isAbleEcouponOrder)),
    BO_ECOUPON(OrderSystemType.BO, OrderType.ECOUPON,
            OrderCommonValidator.commonValidator
                    .and(OrderTypeValidator.isAbleEcouponOrder));

    private final OrderSystemType systemType;
    private final OrderType orderType;
    private final Predicate predicate;

    @Override
    public boolean test(OrderValidationVO vo){
        return predicate.test(vo);
    }

    public static OrderValidator get(OrderRequest orderRequest){
        return Arrays.stream(OrderValidator.values())
                .filter(ele -> ele.orderType == orderRequest.getOrderType() && ele.systemType == orderRequest.getSystemType())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 주문유형이 없습니다!"));
    }

}
