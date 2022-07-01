package com.plateer.ec1.common.code.promotion;

/**
 * 쿠폰종류코드
 */
public enum PRM0004 {

    PRODUCT("10"),
    DUPLICATE("20"),
    CART("30");

    public String code;


    PRM0004(String code) {
        this.code = code;
    }
}
