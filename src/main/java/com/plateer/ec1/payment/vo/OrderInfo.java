package com.plateer.ec1.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderInfo {

    private String ordNo;
    private String goodName;
    private String buyerName;
    private String buyerEmail;

}
