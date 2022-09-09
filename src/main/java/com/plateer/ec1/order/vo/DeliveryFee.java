package com.plateer.ec1.order.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DeliveryFee {

    private Long orgDvAmt;
    private Long dvBnfAmt;
    private String dvAmtTpCd; // 배송비구분코드

}
