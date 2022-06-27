package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.promotion.vo.req.AvailableCouponCountReqVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
    int getAvailableCouponCountByPrmNo(AvailableCouponCountReqVO reqVO);
}
