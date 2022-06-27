package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.service.CalculationService;
import com.plateer.ec1.promotion.vo.req.PromotionRequestVO;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PromotionController {

    private final CalculationService calculationService;

    public BaseResponseVO getApplyData(PromotionRequestVO reqVO){
        return calculationService.getApplyData(reqVO);
    }

}
