package com.plateer.ec1.common.code.order;

/**
 * [주문] 배송비정책구분코드
 */

public enum OPT0015 {

    FREE("10"),
    CHARGE("20");

    public String code;

    OPT0015(String code) {
        this.code = code;
    }

}
