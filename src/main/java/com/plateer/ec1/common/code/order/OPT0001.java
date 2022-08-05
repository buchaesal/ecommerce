package com.plateer.ec1.common.code.order;

/**
 * [주문] 주문유형코드
 */

public enum OPT0001 {

    GENERAL("10"),
    MOBILE_COUPON("20");

    public String code;

    OPT0001(String code) {
        this.code = code;
    }

}
