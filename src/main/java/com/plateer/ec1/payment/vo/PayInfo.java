package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PayInfo {

    @NotNull
    private Long payAmount;
    @NotNull
    private PaymentType paymentType;
    private String bankCode;
    private String depositorName;

}
