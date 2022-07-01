package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.factory.CalculatorFactory;
import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
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
    public void downloadCoupon(@RequestBody CouponRequest request){
        couponService.downloadCoupon(request);
    }

    /**
     * 1차쿠폰 혜택계산
     */
    @PostMapping("/productCoupon")
    public BaseResponseVO getProductCouponCalculation(@RequestBody PromotionRequest request){
        return calculatorFactory.getPromotionCalculationData(request.getPromotionType()).getCalculationData(request);
    }

    /**
     * 장바구니 쿠폰 혜택계산
     */
    @PostMapping("/cartCoupon")
    public BaseResponseVO getCartCouponCalculation(@RequestBody PromotionRequest request){
        return calculatorFactory.getPromotionCalculationData(request.getPromotionType()).getCalculationData(request);
    }

}
