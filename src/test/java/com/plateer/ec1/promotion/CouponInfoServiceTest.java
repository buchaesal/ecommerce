package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponInfoServiceTest {

    @Autowired
    CouponService couponService;

    @Test
    void downloadCoupon(){
        couponService.downloadCoupon(
                CouponRequest.builder()
                        .prmNo(7L)
                        .mbrNo("test01")
                        .build());
    }

    @Test
    void useCoupon(){
        couponService.useCoupon(
                CouponRequest.builder()
                        .prmNo(2L)
                        .cpnIssNo(10L)
                        .ordNo("O3")
                        .build());
    }

    @Test
    void cancelCoupon(){
        couponService.cancelUsingCoupon(
                CouponRequest.builder()
                .prmNo(2L)
                .mbrNo("test01")
                .cpnIssNo(8L)
                .build()
        );
    }

}