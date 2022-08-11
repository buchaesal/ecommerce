package com.plateer.ec1.order.context;

import com.plateer.ec1.order.enums.OrderValidator;
import com.plateer.ec1.order.mapper.OrderDao;
import com.plateer.ec1.order.service.OrderHistoryService;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.vo.OrderVO;
import com.plateer.ec1.order.vo.OrderValidationVO;
import com.plateer.ec1.payment.service.PayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderContext {

    private final OrderDao orderDao;
    private final PayService payService;
    private final OrderHistoryService orderHistoryService;

    @Transactional
    public void execute(DataStrategy dataStrategy, AfterStrategy afterStrategy, OrderRequest orderRequest){

        Long logSeq = orderHistoryService.insertOrderHistory(orderRequest);

        OrderVO orderVO = null;
        Exception exception = null;

        try {
            List<OrderProductView> productViewList = orderDao.selectOrderProductViewList(orderRequest.getProductList());

            // validation
            OrderValidator.get(orderRequest).test(new OrderValidationVO(orderRequest, productViewList));

            // 데이터 생성
            orderVO = dataStrategy.create(orderRequest);

            // 결제
            payService.approve(orderRequest.getPaymentRequest());

            // 주문 데이터 입력
            orderHistoryService.insertOrderData(orderVO);

            // 금액검증
            amountValidation(orderRequest.getOrdNo());

            // 후처리
            afterStrategy.call(orderRequest, orderVO);

        } catch (Exception ex) {
            exception = ex;
            log.info("error: {}", ex);
        } finally {
            orderHistoryService.updateHistory(logSeq, orderVO, exception);
        }
    }

    private void amountValidation(String ordNo){

        if(!orderDao.validateOrderAmount(ordNo)){
            throw new IllegalArgumentException("주문 금액검증 실패");
        }

    }

}
