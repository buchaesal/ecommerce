package com.plateer.ec1.common.code.order;

/**
 * [주문] 귀책구분코드
 */

public enum OPT0008 {

    CUSTOMER("10"),
    COMPANY("20");

    public String code;

    OPT0008(String code) {
        this.code = code;
    }

}
