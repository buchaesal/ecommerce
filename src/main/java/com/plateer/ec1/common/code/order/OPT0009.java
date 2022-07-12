package com.plateer.ec1.common.code.order;

/**
 * [결제] 결제수단코드
 */

public enum OPT0009 {

    VIRTUAL_ACCOUNT("10"),
    POINT("20");

    public String code;
    OPT0009(String code) {
        this.code = code;
    }
}
