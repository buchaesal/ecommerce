package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.PaymentServiceFactory;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
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

    /**
     * 취소
     * @param reqVO
     */
    public void cancel(PayCancelReqVO reqVO){
        OriginalOrder originalOrder = getOriginalOrder(reqVO);
        paymentServiceFactory.getPaymentService(originalOrder.getPaymentType()).cancelPay(originalOrder);
    }

    /**
     * 원 주문 조회
     * @param reqVO
     * @return
     */
    private OriginalOrder getOriginalOrder(PayCancelReqVO reqVO){
        OriginalOrder originalOrder = new OriginalOrder();
        originalOrder.setPaymentType(PaymentType.INICIS);
        return originalOrder;
    }

    /**
     * 망취소
     * @param netCancelReqVO
     */
    public void netCancel(NetCancelReqVO netCancelReqVO){
        paymentServiceFactory.getPaymentService(netCancelReqVO.getPaymentType()).netCancel(netCancelReqVO);
    }

    /**
     * 입금통보완료 수신 후, 처리
     */
    public void acceptDepositNotice(){
        // 주문상태변경
        // 결제상태변경
    }

}
