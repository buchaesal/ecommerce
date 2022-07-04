package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.factory.CalculatorFactory;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalculationService {

    private final CalculatorFactory calculatorFactory;

    public BaseResponseVO getApplyData(PromotionRequest request){
        return calculatorFactory.getPromotionCalculationData(request.getPromotionType()).getCalculationData(request);
    }

}
