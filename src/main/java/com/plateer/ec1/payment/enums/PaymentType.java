package com.plateer.ec1.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum PaymentType {

    INICIS("00", "00", "10"),
    POINT("", "", "20");

    private final String approveSuccessCode;    // 승인성공코드
    private final String cancelSuccessCode;     // 취소실패코드
    private final String payMnCd;               // 결제수단코드(OPT0009)

    public static PaymentType findPaymentType(String payMnCd){
        return Arrays.stream(PaymentType.values())
                .filter(type -> type.getPayMnCd().equals(payMnCd))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("결제유형을 찾을 수 없습니다."));
    }

}
