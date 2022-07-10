package com.plateer.ec1.payment.vo.req;

import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PaymentRequest {

    @NotNull
    OrderInfo orderInfo;
    @NotNull
    List<@Valid PayInfo> payInfoList;

}
