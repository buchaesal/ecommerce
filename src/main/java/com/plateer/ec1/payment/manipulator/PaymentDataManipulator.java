package com.plateer.ec1.payment.manipulator;

import com.plateer.ec1.payment.vo.OrderPaymentModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentDataManipulator {

    public Long insertOrderPaymentData(OrderPaymentModel model){
        log.info("주문결제데이터 insert - model : {}", model);
        return null;
    }

    public void updateOrderPaymentData(Long id, OrderPaymentModel model){
        log.info("주문결제데이터 update - id : {}, OrderPaymentModel: {}", id, model);
    }

    public void updateOrderPaymentStatusAfterDeposit(OrderPaymentModel model){
        log.info("주문결제 결제상태 완료로 변경 - model : {}", model);
    }

}
