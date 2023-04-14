package com.plateer.ec1.payment.strategy.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateer.ec1.common.component.ApiComponent;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.InicisFactory;
import com.plateer.ec1.payment.service.PaymentDataService;
import com.plateer.ec1.payment.strategy.PaymentStrategy;
import com.plateer.ec1.payment.utils.InicisUtil;
import com.plateer.ec1.payment.vo.OrderPayment;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.api.InicisVirtualAccount;
import com.plateer.ec1.payment.vo.req.CancelRequest;
import com.plateer.ec1.payment.vo.req.NetCancelRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;


@Component
public class Inicis extends PaymentStrategy {

    private final ApiComponent apiComponent;

    public Inicis(PaymentDataService dataService, ApiComponent apiComponent) {
        super(dataService);
        this.apiComponent = apiComponent;
    }

    @Override
    public PaymentType getType(){
        return PaymentType.INICIS;
    }

    @Override
    public void validateAuth(OrderPayment orderPayment) {
    }


    @Override
    public InicisVirtualAccount approve(OrderPayment orderPayment) {

//        MultiValueMap<String, String> requestMap = InicisFactory.inicisVirtualAccountRequest(orderInfo, payInfo);
//        Map<String, String> apiResult = InicisUtil.parseJsonToStringMap(apiComponent.exchangeFormRequest(requestMap, InicisFactory.getApiUrl()));
//        InicisVirtualAccount result = new ObjectMapper().convertValue(apiResult, InicisVirtualAccount.class);
        InicisVirtualAccount result = new InicisVirtualAccount();
        result.setResultCode("00");
        result.validateApprove();

        return result;
    }

    @Override
    public InicisVirtualAccount cancel(CancelRequest request) {

        InicisVirtualAccount result = new InicisVirtualAccount();
        OriginalOrder originalOrder = request.getOriginalOrder();

        if(originalOrder.isCompleteDeposit()){
            // 원주문이 결제완료일때만 취소 if 호출
            MultiValueMap<String, String> requestMap = isPartialCancel(request, originalOrder) ?
                    InicisFactory.partialRefundVirtualAccount(request, originalOrder) :
                    InicisFactory.allRefundVirtualAccount(request, originalOrder);

            Map<String, String> apiResult = InicisUtil.parseJsonToStringMap(apiComponent.exchangeFormRequest(requestMap, InicisFactory.getCancelApiUrl()));
            result = new ObjectMapper().convertValue(apiResult, InicisVirtualAccount.class);
            result.validateCancel();
        }

        return result;

    }

    @Override
    public void afterCancelProcess(CancelRequest request){

        OriginalOrder originalOrder = request.getOriginalOrder();
        // 입금전 부분취소 일때, 부분취소후 남은금액 재결제 가상계좌발급
        if(originalOrder.isBeforeDeposit() && isPartialCancel(request, originalOrder)){
            Long approveAmount = originalOrder.getPayAmt() - request.getCnclAmt();
            approve(OrderPayment.builder()
                    .order(originalOrder.makeOrderInfo())
                    .payment(originalOrder.makePayInfo(approveAmount))
                    .build());
        }

    }

    @Override
    public void netCancel(NetCancelRequest netCancelRequest) {
    }

}
