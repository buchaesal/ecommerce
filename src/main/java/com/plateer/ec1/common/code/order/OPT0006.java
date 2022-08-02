package com.plateer.ec1.common.code.order;

/**
 * [주문] 배송비구분코드
 */

public enum OPT0006 {

    DELIVERY_FEE("10"),
    REFUND_FEE("20"),
    EXCHANGE_FEE("30"),
    ISLAND_FEE("40");

    public String code;

    OPT0006(String code) {
        this.code = code;
    }

}
