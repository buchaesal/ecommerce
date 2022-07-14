package com.plateer.ec1.common.code.order;

import lombok.Getter;

/**
 * [결제] 결제진행상태코드
 */
@Getter
public enum OPT0011 {

    REQUEST_APPROVE("10"),
    COMPLETE_APPROVE("20"),
    COMPLETE_REFUND("30");

    public String code;

    OPT0011(String code) {
        this.code = code;
    }

}
