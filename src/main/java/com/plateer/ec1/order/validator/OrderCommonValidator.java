package com.plateer.ec1.order.validator;

import com.plateer.ec1.order.vo.OrderValidationVO;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;

/**
 * 파라메터 유효성검사 - 공통유효성
 * 1. 상품유효성(재고, 판매상태, 최대/최소 구매제한)
 * 2. 업체유효성(업체 상태)
 * 3. 프로모션(쿠폰적용가능여부)
 * 4. 배송지(배송지 유무)
 */
@Slf4j
public class OrderCommonValidator {

    public static Predicate<OrderValidationVO> commonValidator = (dto) -> {
        log.info("주문 공통 유효성검사 : {}", dto);
        return dto != null;
    };

}
