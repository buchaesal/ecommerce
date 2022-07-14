package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.validation.VirtualAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PayInfo {

    @NotNull
    private Long payAmount;
    @NotNull
    private PaymentType paymentType;
    @NotEmpty(groups = VirtualAccount.class)
    private String bankCode;
    @NotEmpty(groups = VirtualAccount.class)
    private String depositorName;

}
