package com.plateer.ec1.order.vo;

import lombok.Getter;

@Getter
public class DeliveryFee {

    private Long orgDvAmt;
    private Long dvBnfAmt;
    private String dvAmtTpCd; // 배송비구분코드

}
