package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.vo.ProductCoupon;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.PriceDiscountResponseVO;
import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 상품상세 - 이번프로젝트에선 X
 */
@Slf4j
@Component
public class PriceDiscountCalculator implements Calculator {

    public PromotionType getType(){
        return PromotionType.PD;
    }

    @Override
    public PriceDiscountResponseVO getCalculationData(PromotionRequest reqVO) {
        log.info("가격 할인 데이터 조회");
        return null;
    }

    private List<ProductCoupon> getAvailablePromotionData(PromotionRequest reqVO){
        log.info("적용 가능 가격 할인 금액 데이터 조회");
        return null;
    }

    private List<ProductCoupon> calculateDcAmt(List<ProductCoupon> list){
        log.info("가격 할인 적용 금액 계산");
        return null;
    }

}
