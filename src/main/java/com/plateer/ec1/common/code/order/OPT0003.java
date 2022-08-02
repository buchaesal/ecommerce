package com.plateer.ec1.common.code.order;

/**
 * [주문] 주문클레임유형코드
 */

public enum OPT0003 {

    ORDER("O"),
    CANCEL("C"),
    REFUND("R"),
    REFUND_CANCEL("RC"),
    EXCHANGE("X");

    public String code;

    OPT0003(String code) {
        this.code = code;
    }

}
