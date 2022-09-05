package com.plateer.ec1.claim.mapper;

import com.plateer.ec1.claim.vo.ClaimOrderInfo;
import com.plateer.ec1.claim.vo.req.OrderClaimRequest;
import com.plateer.ec1.common.model.order.OpClmInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClaimMapper {

    ClaimOrderInfo getOrderInfo(String ordNo);
    OpClmInfoModel getOrderClaim(OrderClaimRequest request);

}
