package com.plateer.ec1.common.code.order;

/**
 * [결제] 결제진행상태코드
 */

public enum OPT0011 {

    PERIOD("10"),
    DAYS("20");

    public String code;
    OPT0011(String code) {
        this.code = code;
    }
}
