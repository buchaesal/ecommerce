package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import com.plateer.ec1.payment.vo.req.PayCancelReqVO;
import com.plateer.ec1.payment.vo.res.PayApproveResVO;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.factory.PaymentServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayService {

    private final PaymentServiceFactory paymentServiceFactory;

    /**
     * 승인
     * @param payInfo
     * @return
     */
    public PayApproveResVO approve(PayInfo payInfo){
        log.info("결제 승인 - payInfo : {}", payInfo);
        PaymentService service = paymentServiceFactory.getPaymentService(payInfo.getPaymentType());
        service.validateAuth(payInfo);
        return service.approvePay(payInfo);
    }

    /**
     * 취소
     * @param reqVO
     */
    public void cancel(PayCancelReqVO reqVO){
        log.info("결제 취소 요청 - PayCancelReqVO : {}", reqVO);
        OriginalOrder originalOrder = getOriginalOrder(reqVO);
        paymentServiceFactory.getPaymentService(originalOrder.getPaymentType()).cancelPay(originalOrder);
    }

    /**
     * 원 주문 조회
     * @param reqVO
     * @return
     */
    private OriginalOrder getOriginalOrder(PayCancelReqVO reqVO){
        log.info("원 주문 조회");
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
