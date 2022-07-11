package com.plateer.ec1.common.code.order;

/**
 * [결제] 결제구분코드
 */

public enum OPT0010 {

    PERIOD("10"),
    DAYS("20");

    public String code;
    OPT0010(String code) {
        this.code = code;
    }
}
