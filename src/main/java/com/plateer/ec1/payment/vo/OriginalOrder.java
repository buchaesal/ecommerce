package com.plateer.ec1.payment.vo;

import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.payment.enums.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OriginalOrder {

    private String payNo;
    private String ordNo;
    private String payMnCd; // 결제수단코드(OPT0009)
    private String payPrgsScd;
    private Long payAmt;
    private Long rfndAvlAmt;
    private String trsnId;
    private LocalDateTime payCmtDtime;
    private String vrAcct;
    private String vrAcctNm;
    private String vrBnkCd;
    private String vrValDt;
    private String vrValTt;
    private String goodsNm;
    private String ordNm;
    private String rfndBnkCk;
    private String rfndAcctNo;
    private String rfndAcctOwnNm;

    private Long newRfndAvlAmt = 0L;

    public void validateAmount(Long requestCancelAmount){

        if(payAmt < requestCancelAmount){
            throw new IllegalStateException("취소요청금액이 결제금액보다 큽니다.");
        }

        if(isCompleteDeposit()){
            if(rfndAvlAmt < requestCancelAmount){
                throw new IllegalStateException("취소요청금액이 환불가능금액보다 큽니다.");
            }
            newRfndAvlAmt = rfndAvlAmt - requestCancelAmount;
        }

    }

    public boolean isBeforeDeposit(){
        return OPT0011.REQUEST_APPROVE.code.equals(payPrgsScd);
    }

    public boolean isCompleteDeposit(){
        return OPT0011.COMPLETE_APPROVE.code.equals(payPrgsScd);
    }

    public OrderInfo makeOrderInfo(){
        return new OrderInfo(ordNo, goodsNm, ordNm, "");
    }

    public PayInfo makePayInfo(Long payAmount){
        return new PayInfo(payAmount, PaymentType.findPaymentType(payMnCd), vrBnkCd, vrAcctNm);
    }

}
