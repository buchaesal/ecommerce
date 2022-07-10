package com.plateer.ec1.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentType {

    INICIS("00", "00"),
    POINT("", "");

    private final String approveSuccessCode;
    private final String cancelSuccessCode;

}
