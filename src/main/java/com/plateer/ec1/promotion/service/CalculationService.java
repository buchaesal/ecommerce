package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import com.plateer.ec1.promotion.factory.CalculatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final CalculatorFactory factory;

    @Transactional
    public BaseResponseVO getApplyData(PromotionRequest reqVO){
        return factory.getPromotionCalculationData(reqVO.getPromotionType()).getCalculationData(reqVO);
    }

    public void downloadCoupons(){

    }

}
