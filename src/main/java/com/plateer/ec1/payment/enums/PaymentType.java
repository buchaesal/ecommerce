package com.plateer.ec1.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PaymentType {

    INICIS("00", "00", "10"),
    POINT("", "", "20");

    private final String approveSuccessCode;
    private final String cancelSuccessCode;
    private final String payMnCd;

    public static PaymentType findPaymentType(String payMnCd){
        return Arrays.stream(PaymentType.values())
                .filter(type -> type.getPayMnCd().equals(payMnCd))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결제유형을 찾을 수 없습니다."));
    }

}
