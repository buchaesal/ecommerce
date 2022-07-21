package com.plateer.ec1.order.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderBase {

    private String mbrNo;
    private String ordTpCd; // 주문유형코드(OPT0001)
    private String ordSysCcd;
    private String ordNm;
    private String ordSellNo;
    private String ordAddr;
    private String ordAddrDtl;

}
