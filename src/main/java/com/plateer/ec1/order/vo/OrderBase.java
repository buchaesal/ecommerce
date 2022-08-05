package com.plateer.ec1.order.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderBase {

    @NotEmpty
    private String ordNo;
    @NotEmpty
    private String mbrNo;
    @NotEmpty
    private String ordTpCd; // 주문유형코드(OPT0001)
    @NotEmpty
    private String ordSysCcd;
    @NotEmpty
    private String ordNm;
    @NotEmpty
    private String ordSellNo;
    private String ordAddr;
    private String ordAddrDtl;
    private String rfndBnkCk;
    private String rfndAcctNo;
    private String rfndAcctOwnNm;

}
