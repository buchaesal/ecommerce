package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.vo.req.PromotionRequestVO;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import com.plateer.ec1.promotion.service.PromotionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PromotionController {

    private final PromotionService promotionService;

    public BaseResponseVO getApplyData(PromotionRequestVO reqVO){
        return promotionService.getApplyData(reqVO);
    }

}
