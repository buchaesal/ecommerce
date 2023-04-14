package com.plateer.ec1.payment.strategy.impl;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.service.PaymentDataService;
import com.plateer.ec1.payment.strategy.PaymentStrategy;
import com.plateer.ec1.payment.vo.OrderPayment;
import com.plateer.ec1.payment.vo.api.PointPayment;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import com.plateer.ec1.payment.vo.req.NetCancelRequest;
import org.springframework.stereotype.Component;

@Component
public class Point extends PaymentStrategy {

    public Point(PaymentDataService dataService) {
        super(dataService);
    }

    public PaymentType getType(){
        return PaymentType.POINT;
    }

    @Override
    public void validateAuth(OrderPayment orderPayment) {
    }

    @Override
    public PointPayment approve(OrderPayment orderPayment) {
        // 프로모션에서 제공하는 포인트 승인 api 호출
        return new PointPayment();
    }

    @Override
    public PointPayment cancel(CancelRequest request) {
        // 프로모션에 제공하는 포인트 취소 api 호출
        return null;
    }

    public void netCancel(NetCancelRequest netCancelRequest) {
    }

}
