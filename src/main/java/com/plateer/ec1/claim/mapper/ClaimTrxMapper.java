package com.plateer.ec1.claim.mapper;

import com.plateer.ec1.claim.vo.ClaimOrderInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClaimTrxMapper {

    ClaimOrderInfo getOrderInfo(String ordNo);

}
