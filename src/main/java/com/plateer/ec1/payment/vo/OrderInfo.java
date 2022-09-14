package com.plateer.ec1.payment.vo;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class OrderInfo {

    @NotEmpty
    private String ordNo;
    private String goodsNm;
    private String ordNm;
    private String ordEmail;

}
