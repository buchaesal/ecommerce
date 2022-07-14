package com.plateer.ec1.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class OrderInfo {

    @NotEmpty
    private String ordNo;
    private String goodName;
    private String buyerName;
    private String buyerEmail;

}
