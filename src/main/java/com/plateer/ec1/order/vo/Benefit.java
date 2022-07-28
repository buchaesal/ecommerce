package com.plateer.ec1.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Benefit {

    private String prmNo;
    private String cpnKndCd; // 쿠폰종류코드(PRM0004)
    private String cpnIssNo;
    private String degrCcd; // 차수구분코드(PRM0012)

}
