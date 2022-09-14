package com.plateer.ec1.payment.vo.req;

import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Builder
public class PaymentRequest {

    @NotNull
    @Valid
    OrderInfo orderInfo;
    @NotNull
    List<@Valid @NotNull PayInfo> payInfoList;

}
