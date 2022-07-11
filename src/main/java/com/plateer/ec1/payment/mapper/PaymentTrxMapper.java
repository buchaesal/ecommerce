package com.plateer.ec1.payment.mapper;

import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentTrxMapper {

    void insertOrderPayment(OpPayInfoModel model);

}
