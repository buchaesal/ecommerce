package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class PayInfo {

    private Long payAmount;
    private String bankCode;
    private PaymentType paymentType;
    private String depositorName;

}
