package com.plateer.ec1.payment.strategy;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentDataService;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PaymentMethod;
import com.plateer.ec1.payment.vo.api.PaymentResultBase;
import com.plateer.ec1.payment.vo.req.NetCancelRequest;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class PaymentStrategy {

    private final PaymentDataService dataService;

    abstract public PaymentType getType();

    abstract public void validateAuth(PaymentMethod paymentMethod);

    abstract public PaymentResultBase approve(Order order, PaymentMethod paymentMethod);


    abstract public PaymentResultBase cancel(CancelRequest request);

    abstract public void netCancel(NetCancelRequest netCancelRequest);

    public boolean isPartialCancel(CancelRequest request, OriginalOrder originalOrder){
        return originalOrder.getPayAmt() != request.getCnclAmt();
    }

    public void afterCancelProcess(CancelRequest request){
    }

}
