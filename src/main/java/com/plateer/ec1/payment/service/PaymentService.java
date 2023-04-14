package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.context.ApproveContext;
import com.plateer.ec1.payment.context.CancelContext;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.PaymentStrategyFactory;
import com.plateer.ec1.payment.vo.OrderPayment;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import com.plateer.ec1.payment.vo.req.ChangeDepositCompleteRequest;
import com.plateer.ec1.payment.vo.req.NetCancelRequest;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class PaymentService {

    private final ApproveContext approveContext;
    private final CancelContext cancelContext;
    private final PaymentDataService dataService;
    private final PaymentStrategyFactory paymentStrategyFactory;

    @Validated
    @Transactional
    public List<PayApproveResponse> approve(@Valid PaymentRequest paymentRequest){

        List<PayApproveResponse> resultList = new ArrayList<>();
        paymentRequest.getPaymentList().forEach(payment -> {
            OrderPayment orderPayment = OrderPayment.builder().order(paymentRequest.getOrder()).payment(payment).build();
            approveContext.execute(paymentStrategyFactory.getPaymentStrategy(payment.getPaymentType()), orderPayment);
        });

        return resultList;

    }

    @Validated
    @Transactional
    public void manipulateAmount(@Valid CancelRequest request){

        OriginalOrder originalOrder = Optional.ofNullable(dataService.getOriginalOrder(request))
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
        originalOrder.validateAmount(request.getCnclAmt());
        dataService.updateCancelRefundAmount(request, originalOrder);

    }

    @Validated
    @Transactional
    public void cancel(@Valid CancelRequest request){
        OriginalOrder originalOrder = dataService.getOriginalOrder(request);
        cancelContext.execute(paymentStrategyFactory.getPaymentStrategy(PaymentType.findPaymentType(originalOrder.getPayMnCd())), request);
    }

    @Validated
    @Transactional
    public void completeDeposit(@NotNull Map<String, String> apiResultMap){
        dataService.changeDepositCompleteStatus(new ChangeDepositCompleteRequest(apiResultMap));
    }

    public void netCancel(NetCancelRequest netCancelRequest){
    }

}
