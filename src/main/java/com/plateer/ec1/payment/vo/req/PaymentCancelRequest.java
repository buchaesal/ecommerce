package com.plateer.ec1.payment.vo.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PaymentCancelRequest {

    @NotEmpty
    private String orrNo;
    @NotEmpty
    private String clmNo;
    @NotNull
    private Long cnclAmt;

}
