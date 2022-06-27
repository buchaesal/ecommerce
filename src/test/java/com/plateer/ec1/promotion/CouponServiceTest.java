package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.vo.req.CouponReqVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    CouponService couponService;

    @Test
    void downloadCoupon(){
        couponService.downloadCoupon(CouponReqVO.builder().prmNo(1L).mbrNo("test01").build());
    }
}