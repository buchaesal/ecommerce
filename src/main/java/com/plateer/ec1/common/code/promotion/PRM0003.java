package com.plateer.ec1.common.code.promotion;

/**
 * 할인구분코드
 */
public enum PRM0003 {

    PRICE("10"),
    PERCENT("20");

    public String code;

    PRM0003(String code) {
        this.code = code;
    }

}
