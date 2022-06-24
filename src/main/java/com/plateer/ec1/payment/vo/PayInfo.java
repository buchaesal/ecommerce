package com.plateer.ec1.payment.vo;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PayInfo {
    private String orrNo;
    private Long payAmount;
    private String type;
    private PaymentType paymentType;
}
