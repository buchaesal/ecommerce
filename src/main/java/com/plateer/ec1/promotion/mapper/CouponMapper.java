package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.req.AvailableCouponCountReqVO;
import com.plateer.ec1.promotion.vo.res.CouponCountResVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
    CouponCountResVO getAvailableCouponCountByPrmNo(AvailableCouponCountReqVO reqVO);
    boolean validatePromotionPeriod(Long prmNo);
    CouponInfo getCouponInfo(Long cpnIssNo);
}
