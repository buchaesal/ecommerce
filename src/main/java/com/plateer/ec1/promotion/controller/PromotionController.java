package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.factory.CalculatorFactory;
import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.vo.req.CouponReqVO;
import com.plateer.ec1.promotion.vo.req.PromotionReqVO;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("promotion")
@RequiredArgsConstructor
public class PromotionController {

    private final CouponService couponService;
    private final CalculatorFactory calculatorFactory;

    /**
     * 쿠폰 다운로드
     */
    @PostMapping("/downloadCoupon")
    public void downloadCoupon(@RequestBody CouponReqVO reqVO){
        couponService.downloadCoupon(reqVO);
    }

    /**
     * 1차쿠폰 혜택계산
     */
    @PostMapping("/productCoupon")
    public BaseResponseVO getProductCouponCalculation(@RequestBody PromotionReqVO reqVO){
        return calculatorFactory.getPromotionCalculationData(reqVO.getPromotionType()).getCalculationData(reqVO);
    }

}
