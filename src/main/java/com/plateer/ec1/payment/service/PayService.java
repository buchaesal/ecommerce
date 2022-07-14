package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.PaymentServiceFactory;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.req.ChangeDepositCompleteRequest;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import com.plateer.ec1.payment.vo.req.PayCancelReqVO;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import com.plateer.ec1.payment.vo.res.PayApproveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PayService {

    private final PaymentTrxMapper paymentTrxMapper;
    private final PaymentServiceFactory paymentServiceFactory;

    @Transactional
    public List<PayApproveResponse> approve(PaymentRequest paymentRequest){

        List<PayApproveResponse> resultList = new ArrayList<>();
        OrderInfo orderInfo = paymentRequest.getOrderInfo();

        paymentRequest.getPayInfoList().forEach(payInfo -> {
            PayApproveResponse response = paymentServiceFactory
                    .getPaymentService(payInfo.getPaymentType())
                    .executePayment(orderInfo, payInfo);
            resultList.add(response);
        });

        return resultList;

    }

    public void cancel(PayCancelReqVO reqVO){
        OriginalOrder originalOrder = getOriginalOrder(reqVO);
        paymentServiceFactory.getPaymentService(originalOrder.getPaymentType()).cancelPay(originalOrder);
    }

    private OriginalOrder getOriginalOrder(PayCancelReqVO reqVO){
        OriginalOrder originalOrder = new OriginalOrder();
        originalOrder.setPaymentType(PaymentType.INICIS);
        return originalOrder;
    }

    public void netCancel(NetCancelReqVO netCancelReqVO){
        paymentServiceFactory.getPaymentService(netCancelReqVO.getPaymentType()).netCancel(netCancelReqVO);
    }

    @Transactional
    public void completeDeposit(ChangeDepositCompleteRequest request){
        paymentTrxMapper.updateDepositCompleteStatus(request);
    }

}
