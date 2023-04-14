package com.plateer.ec1.payment.vo.req;

import com.plateer.ec1.payment.vo.OriginalOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CancelRequest {

    @NotEmpty
    private String ordNo;
    @NotEmpty
    private String clmNo;
    @NotNull
    private Long cnclAmt;

    OriginalOrder originalOrder;

}
