package com.plateer.ec1.payment.vo.res;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PayApproveResponse {

    private PaymentType paymentType;
    private String ablePartialCancelYn;

}
