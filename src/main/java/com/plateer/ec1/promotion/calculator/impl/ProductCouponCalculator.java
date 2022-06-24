package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.vo.ProductCouponVO;
import com.plateer.ec1.promotion.vo.req.PromotionRequestVO;
import com.plateer.ec1.promotion.vo.res.ProductCouponResponseVO;
import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 장바구니에서 상품별 쿠폰 적용 부분에서.
 */
@Slf4j
@Component
public class ProductCouponCalculator implements Calculator {

    public PromotionType getType(){
        return PromotionType.PRD_CUP;
    }

    @Override
    public ProductCouponResponseVO getCalculationData(PromotionRequestVO reqVO) {
        log.info("상품 쿠폰 데이터 조회");
        return null;
    }

    private List<ProductCouponVO> getAvailablePromotionData(PromotionRequestVO reqVO){
        log.info("적용 가능 상품 쿠폰 데이터 조회");
        return null;
    }

    private List<ProductCouponVO> calculateDcAmt(List<ProductCouponVO> list){
        log.info("상품 쿠폰 할인 적용 금액 계산");
        return null;
    }

    private List<ProductCouponVO> calculateMaxBenefit(List<ProductCouponVO> list){
        log.info("상품 쿠폰 최대혜택 계산");
        return null;
    }

}
