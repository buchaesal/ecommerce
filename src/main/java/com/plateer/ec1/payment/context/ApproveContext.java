package com.plateer.ec1.payment.context;

import com.plateer.ec1.payment.service.PaymentDataService;
import com.plateer.ec1.payment.strategy.PaymentStrategy;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.OrderPayment;
import com.plateer.ec1.payment.vo.PaymentMethod;
import com.plateer.ec1.payment.vo.api.PaymentResultBase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApproveContext {

    private final PaymentDataService paymentDataService;

    public void execute(PaymentStrategy strategy, OrderPayment orderPayment){

        Order order = orderPayment.getOrder();
        PaymentMethod paymentMethod = orderPayment.getPaymentMethod();

        // 인증값 검증
        strategy.validateAuth(paymentMethod);

        // 승인요청
        PaymentResultBase result = strategy.approve(order, paymentMethod);

        // PAY_BASE INSERT
        paymentDataService.saveOrderPaymentData(result.makeApproveInsertModel(order, paymentMethod));
    }
}
