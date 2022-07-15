package com.plateer.ec1.payment.service;

import com.plateer.ec1.common.code.order.OPT0010;
import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.mapper.PaymentMapper;
import com.plateer.ec1.payment.mapper.PaymentTrxMapper;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.req.ChangeDepositCompleteRequest;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderPaymentDataService {

    private final PaymentMapper paymentMapper;
    private final PaymentTrxMapper paymentTrxMapper;

    /*
    주문결제 테이블 row insert
     */
    public void saveOrderPaymentData(OpPayInfoModel model){
        paymentTrxMapper.insertOrderPayment(model);
    }

    /*
    입금완료 통보 후, 결제 완료 상태 update
     */
    public void changeDepositCompleteStatus(ChangeDepositCompleteRequest request){
        paymentTrxMapper.updateDepositCompleteStatus(request);
    }

    /*
    주문번호로 원주문건 데이터 조회
     */
    public OriginalOrder getOriginalOrder(PaymentCancelRequest request){
        return paymentMapper.getOriginalOrder(request.getOrrNo());
    }

    /*
    취소, 환불가능금액 update
     */
    public void updateCancelRefundAmount(PaymentCancelRequest request, OriginalOrder originalOrder){

        OpPayInfoModel model = new OpPayInfoModel();
        model.setCnclAmt(request.getCnclAmt());
        model.setRfndAvlAmt(originalOrder.getNewRfndAvlAmt());
        model.setPayNo(originalOrder.getPayNo());

        paymentTrxMapper.updateCancelRefundAmount(model);

    }

    private OpPayInfoModel makeCancelDataModel(PaymentCancelRequest request, OriginalOrder originalOrder){

        OpPayInfoModel model = new OpPayInfoModel();
        BeanUtils.copyProperties(originalOrder, model);
        model.setClmNo(request.getClmNo());
        model.setCnclAmt(request.getCnclAmt());
        model.setPayCcd(OPT0010.CANCEL.code);
        model.setPayPrgsScd(OPT0011.COMPLETE_REFUND.code);

        return model;

    }

}
