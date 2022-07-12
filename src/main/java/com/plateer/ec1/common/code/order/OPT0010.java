package com.plateer.ec1.common.code.order;

/**
 * [결제] 결제구분코드
 */

public enum OPT0010 {

    APPROVE("10"),
    CANCEL("20");

    public String code;
    OPT0010(String code) {
        this.code = code;
    }
}
