package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.vo.CouponProductVO;
import com.plateer.ec1.promotion.vo.req.PromotionReqVO;
import com.plateer.ec1.promotion.vo.res.CartCouponResponseVO;
import com.plateer.ec1.promotion.enums.PromotionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 주문서에서 조회
 */
@Slf4j
@Component
public class CartCouponCalculator implements Calculator {

    public PromotionType getType(){
        return PromotionType.CART_CUP;
    }

    @Override
    public CartCouponResponseVO getCalculationData(PromotionReqVO reqVO) {
        log.info("장바구니 쿠폰 데이터 조회");
        return null;
    }

    public List<CouponProductVO> getAvailablePromotionData(List<CouponProductVO> list){
        log.info("적용 가능 장바구니 쿠폰 데이터 조회");
        return null;
    }

    public List<CouponProductVO> calculateDcAmt(List<CouponProductVO> list){
        log.info("장바구니 쿠폰 할인 적용 금액 계산");
        return null;
    }

    public List<CouponProductVO> calculateMaxBenefit(List<CouponProductVO> list){
        log.info("장바구니 쿠폰 최대혜택 계산 후, Y/N set");
        return null;
    }

}
