package com.plateer.ec1.promotion.vo.req;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CouponRequest {
    private String mbrNo;
    private Long prmNo;
    private String ordNo;
    private Long cpnIssNo;
}
