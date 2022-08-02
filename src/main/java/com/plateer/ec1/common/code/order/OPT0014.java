package com.plateer.ec1.common.code.order;

/**
 * [주문] 배송회수구분코드
 */

public enum OPT0014 {

    DELIVERY("D"),
    RETRIEVE("R");

    public String code;

    OPT0014(String code) {
        this.code = code;
    }

}
