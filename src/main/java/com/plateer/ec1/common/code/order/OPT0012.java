package com.plateer.ec1.common.code.order;

/**
 * [주문] 처리구분코드
 */
public enum OPT0012 {

    SUCCESS("S"),
    CREATE("FD"),
    VALIDATE("FV"),
    PAYMENT("FP");

    public String code;

    OPT0012(String code) {
        this.code = code;
    }

}
