package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.NetCancelReqVO;
import com.plateer.ec1.payment.vo.res.PayApproveResVO;
import com.plateer.ec1.payment.enums.PaymentType;

public interface PaymentService {
    PaymentType getType();
    void validateAuth(PayInfo payInfo);
    PayApproveResVO approvePay(PayInfo payInfo);
    void cancelPay(OriginalOrder originalOrder);
    void netCancel(NetCancelReqVO netCancelReqVO);

}
