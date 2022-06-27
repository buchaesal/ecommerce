package com.plateer.ec1.promotion.controller;

import com.plateer.ec1.promotion.service.CalculationService;
import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.vo.req.CouponReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("promotion")
@RequiredArgsConstructor
public class PromotionController {

    private final CalculationService calculationService;
    private final CouponService couponService;

    @PostMapping("/downloadCoupon")
    public void downloadCoupon(@RequestBody CouponReqVO reqVO){
        couponService.downloadCoupon(reqVO);
    }

//    public BaseResponseVO getApplyData(PromotionRequestVO reqVO){
//        return calculationService.getApplyData(reqVO);
//    }

}
