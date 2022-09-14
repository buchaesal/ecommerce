package com.plateer.ec1.payment.vo;

import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
public class PayInfo {

    @NotNull
    private Long payAmount;
    @NotNull
    private PaymentType paymentType;
    private String bankCode;
    private String depositorName;

    public OPT0011 getPayPrgsScdApprove(){
        return PaymentType.POINT == paymentType ? OPT0011.COMPLETE_APPROVE : OPT0011.REQUEST_APPROVE;
    }

}
