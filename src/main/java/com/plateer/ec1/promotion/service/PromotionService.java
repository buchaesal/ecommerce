package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.vo.req.PromotionRequestVO;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import com.plateer.ec1.promotion.factory.CalculatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final CalculatorFactory factory;

    @Transactional
    public BaseResponseVO getApplyData(PromotionRequestVO reqVO){
        return factory.getPromotionCalculationData(reqVO.getPromotionType()).getCalculationData(reqVO);
    }

}
