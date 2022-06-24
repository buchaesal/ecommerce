package com.plateer.ec1.order.validator;

import com.plateer.ec1.order.vo.OrderValidationVO;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

/**
 * 파라미터 유효성검사 - 시스템별 유효성
 * 1. 상품유효성(구매가능 시스템, 성인상품 여부 등..)
 * 2. 주문기본 유효성(시스템별 주문 가능한 주문유형, 시스템별 파라미터 확인)
 */
@Slf4j
public class OrderSystemValidator {

    public static Predicate<OrderValidationVO> isAbleBoOrder = (dto) -> {
        log.info("BO 주문 가능 여부 validation : {}", dto);
        return dto != null;
    };

}
