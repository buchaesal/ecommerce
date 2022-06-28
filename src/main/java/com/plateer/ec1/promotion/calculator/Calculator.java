package com.plateer.ec1.promotion.calculator;

import com.plateer.ec1.promotion.vo.req.PromotionReqVO;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import com.plateer.ec1.promotion.enums.PromotionType;

public interface Calculator {
    PromotionType getType();
    BaseResponseVO getCalculationData(PromotionReqVO reqVO);
}
