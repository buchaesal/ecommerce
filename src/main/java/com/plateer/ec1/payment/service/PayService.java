package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.PaymentServiceFactory;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.req.ChangeDepositCompleteRequest;
import com.plateer.ec1.payment.vo.req.NetCancelRequest;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
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
public class PayService {

    private final OrderPaymentDataService dataService;
    private final PaymentServiceFactory paymentServiceFactory;

    @Validated
    @Transactional
    public List<PayApproveResponse> approve(@Valid PaymentRequest paymentRequest){

        List<PayApproveResponse> resultList = new ArrayList<>();
        OrderInfo orderInfo = paymentRequest.getOrderInfo();

        paymentRequest.getPayInfoList().forEach(payInfo -> {
            PayApproveResponse response = paymentServiceFactory
                    .getPaymentService(payInfo.getPaymentType())
                    .executeApproveProcess(orderInfo, payInfo);
            resultList.add(response);
        });

        return resultList;

    }

    @Validated
    @Transactional
    public void manipulateAmount(@Valid PaymentCancelRequest request){

        OriginalOrder originalOrder = Optional.ofNullable(dataService.getOriginalOrder(request))
                .orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
        originalOrder.validateAmount(request.getCnclAmt());
        dataService.updateCancelRefundAmount(request, originalOrder);

    }

    @Validated
    @Transactional
    public void cancel(@Valid PaymentCancelRequest request){

        OriginalOrder originalOrder = dataService.getOriginalOrder(request);
        paymentServiceFactory.getPaymentService(PaymentType.findPaymentType(originalOrder.getPayMnCd()))
                .executeCancelProcess(request, originalOrder);

    }

    @Validated
    @Transactional
    public void completeDeposit(@NotNull Map<String, String> apiResultMap){
        dataService.changeDepositCompleteStatus(new ChangeDepositCompleteRequest(apiResultMap));
    }

    public void netCancel(NetCancelRequest netCancelRequest){
    }

}
