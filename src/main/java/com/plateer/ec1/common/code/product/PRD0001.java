package com.plateer.ec1.common.code.product;

/**
 * [상품] 상품판매유형코드
 */
public enum PRD0001 {

    GENERAL("10"),
    MOBILE_COUPON("20");

    public String code;
    PRD0001(String code) {
        this.code = code;
    }

}
