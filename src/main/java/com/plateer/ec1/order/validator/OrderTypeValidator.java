package com.plateer.ec1.order.validator;

import com.plateer.ec1.order.vo.OrderValidationVO;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

/**
 * 파라미터 유효성검사 - 주문유형별
 * 필수값 유효성(주문유형별 필수데이터 유무, 주문유형별 상품 확인 등..)
 */
@Slf4j
public class OrderTypeValidator {

    public static Predicate<OrderValidationVO> isAbleEcouponOrder = (dto) -> {
        log.info("모바일쿠폰 주문가능상품여부 validation : {}", dto);
        return dto != null;
    };

}
