package com.plateer.ec1.common.code.delivery;

/**
 * [배송] 배송방법구분코드
 */

public enum DVP0001 {

    DELIVERY("10"),
    NON_DELIVERY("20");

    public String code;

    DVP0001(String code) {
        this.code = code;
    }

}
