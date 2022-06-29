package com.plateer.ec1.common.code;

import lombok.RequiredArgsConstructor;

/**
 * [프로모션] 기간구분코드
 */

public enum PRM0002 {

    PERIOD("10"),
    DAYS("20");

    public String code;
    PRM0002(String code) {
        this.code = code;
    }
}
