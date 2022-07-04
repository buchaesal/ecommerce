package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.service.CalculationService;
import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.validation.Download;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.BaseResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("promotion")
@RequiredArgsConstructor
public class PromotionController {

    private final CouponService couponService;
    private final CalculationService calculationService;

    /**
     * 쿠폰 다운로드
     */
    @Validated(Download.class)
    @PostMapping("/downloadCoupon")
    public void downloadCoupon(@RequestBody CouponRequest request){
        couponService.downloadCoupon(request);
    }

    /**
     * 혜택계산 (PromotionType으로 선택)
     */
    @PostMapping("/productCoupon")
    public BaseResponseVO getCalculation(@RequestBody PromotionRequest request){
        return calculationService.getApplyData(request);
    }

}
