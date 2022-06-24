package com.plateer.ec1.payment.vo.req;

import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Data;

@Data
public class NetCancelReqVO {
    private PaymentType paymentType;
}
