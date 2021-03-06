package com.plateer.ec1.payment.service.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.OrderPaymentDataService;
import com.plateer.ec1.payment.service.PaymentService;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.PointPayment;
import com.plateer.ec1.payment.vo.req.NetCancelRequest;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import org.springframework.stereotype.Component;

@Component
public class Point extends PaymentService<PointPayment> {

    public Point(OrderPaymentDataService dataService) {
        super(dataService);
    }

    public PaymentType getType(){
        return PaymentType.POINT;
    }

    public void validateAuth(PayInfo payInfo) {
    }

    @Override
    public PointPayment approve(OrderInfo orderInfo, PayInfo payInfo) {
        return new PointPayment();
    }

    @Override
    public PointPayment cancel(PaymentCancelRequest request, OriginalOrder originalOrder) {
        return null;
    }

    public void cancel(OriginalOrder originalOrder) {
    }

    public void netCancel(NetCancelRequest netCancelRequest) {
    }

}
