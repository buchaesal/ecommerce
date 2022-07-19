package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.api.PaymentResultBase;
import com.plateer.ec1.payment.vo.req.NetCancelRequest;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@RequiredArgsConstructor
public abstract class PaymentService<T extends PaymentResultBase> {

    private final OrderPaymentDataService dataService;

    abstract public PaymentType getType();

    PayApproveResponse executeApproveProcess(OrderInfo orderInfo, @Valid PayInfo payInfo){

         // 인증값 검증
        validateAuth(payInfo);

        // 승인요청
        T result = approve(orderInfo, payInfo);

        // 주문결제 데이터 저장
        dataService.saveOrderPaymentData(result.makeApproveInsertModel(orderInfo, payInfo));

        return new PayApproveResponse(payInfo.getPaymentType(), result.getAblePartialCancelYn());

    }

    abstract public void validateAuth(PayInfo payInfo);

    abstract public T approve(OrderInfo orderInfo, PayInfo payInfo);

    void executeCancelProcess(PaymentCancelRequest request, OriginalOrder originalOrder){

        // 취소요청
        T result = cancel(request, originalOrder);

        // 주문결제 취소데이터 저장
        dataService.saveOrderPaymentData(result.makeCancelInsertModel(request, originalOrder));

        // 후처리
        afterCancelProcess(request, originalOrder);

    }

    abstract public T cancel(PaymentCancelRequest request, OriginalOrder originalOrder);

    abstract public void netCancel(NetCancelRequest netCancelRequest);

    public boolean isPartialCancel(PaymentCancelRequest request, OriginalOrder originalOrder){
        return originalOrder.getPayAmt() != request.getCnclAmt();
    }

    public void afterCancelProcess(PaymentCancelRequest request, OriginalOrder originalOrder){
    }

}
