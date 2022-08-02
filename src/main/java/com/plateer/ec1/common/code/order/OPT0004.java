package com.plateer.ec1.common.code.order;

/**
 * [주문] 주문진행상태코드
 */

public enum OPT0004 {

    ORDER_WAITING("10"),
    ORDER_COMPLETE("20"),
    CANCEL_REQUEST("30"),
    CANCEL_COMPLETE("40"),
    DELIVERY_COMPLETE("50"),
    REFUND_ACCEPT("60"),
    EXCHANGE_ACCEPT("70"),
    REFUND_CANCEL_COMPLETE("80");

    public String code;

    OPT0004(String code) {
        this.code = code;
    }

}
