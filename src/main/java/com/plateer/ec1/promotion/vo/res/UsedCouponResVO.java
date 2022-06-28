package com.plateer.ec1.promotion.vo.res;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsedCouponResVO {
    private LocalDateTime prmEndDt;
    private Long cpnIssNo;
}
