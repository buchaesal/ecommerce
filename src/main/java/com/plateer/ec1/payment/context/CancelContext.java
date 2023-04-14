package com.plateer.ec1.payment.context;

import com.plateer.ec1.payment.service.PaymentDataService;
import com.plateer.ec1.payment.strategy.PaymentStrategy;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.api.PaymentResultBase;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelContext {

    private final PaymentDataService paymentDataService;

    public void execute(PaymentStrategy strategy, CancelRequest request){

        // 취소요청
        PaymentResultBase result = strategy.cancel(request);

        // 주문결제 취소데이터 저장
        paymentDataService.saveOrderPaymentData(result.makeCancelInsertModel(request));

        // 후처리
        strategy.afterCancelProcess(request);
    }
}
