package com.plateer.ec1.common.code.product;

/**
 * [상품] 상품배송유형코드
 */
public enum PRD0002 {

    GENERAL("10"),
    NO_SHIPPING("20");

    public String code;
    PRD0002(String code) {
        this.code = code;
    }

}
