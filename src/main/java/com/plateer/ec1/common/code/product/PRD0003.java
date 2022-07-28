package com.plateer.ec1.common.code.product;

/**
 * [상품] 상품진행상태코드
 */
public enum PRD0003 {

    BEFORE_APPROVE("10"),
    ON_SAIL("20"),
    PAUSE("30"),
    STOP_SELLING("40");

    public String code;
    PRD0003(String code) {
        this.code = code;
    }

}
