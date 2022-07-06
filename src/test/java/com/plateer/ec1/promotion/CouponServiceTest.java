package com.plateer.ec1.promotion;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.CouponMapper;
import com.plateer.ec1.promotion.service.CouponService;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    CouponService couponService;

    @Autowired
    CouponMapper couponMapper;

    @Test
    @DisplayName("쿠폰 다운로드 필수값 테스트")
    void downloadCoupon1(){

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            couponService.downloadCoupon(
                    CouponRequest.builder()
                            .prmNo(1L)
//                        .mbrNo("test01")
                            .build());
        });

    }

    @Test
    @DisplayName("쿠폰 다운로드 기간 경계테스트")
    void downloadCoupon2(){

        //prm_no = 3  : 6/1~6/30
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            couponService.downloadCoupon(
                    CouponRequest.builder()
                            .prmNo(3L)
                            .mbrNo("test01")
                            .build());
        });

    }

    @Test
    @DisplayName("쿠폰 다운로드 테스트")
    void downloadCoupon3(){

        Long prmNo = 1L;
        String mbrNo = "test01";

        CcCpnIssueModel model = couponService.downloadCoupon(
                CouponRequest.builder()
                        .prmNo(prmNo)
                        .mbrNo(mbrNo)
                        .build());

        CouponInfo couponInfo = getCouponInfo(model.getCpnIssNo());

        Assertions.assertEquals(prmNo, couponInfo.getPrmNo());
        Assertions.assertEquals(mbrNo, couponInfo.getMbrNo());
        Assertions.assertEquals(null, couponInfo.getCpnUseDt());

    }

    @Test
    @DisplayName("쿠폰사용 필수값 체크")
    void useCoupon1(){

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            couponService.useCoupon(
                    CouponRequest.builder()
                    .cpnIssNo(1L)
                    .mbrNo("test01")
//                        .ordNo("O3")
                    .build());
        });

    }


    @Test
    @DisplayName("쿠폰사용 - 프로모션 기간 경계테스트")
    void useCoupon2(){

        //프로모션기간 prm_no=6 : 6/1~6/30
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            couponService.useCoupon(
                    CouponRequest.builder()
                    .mbrNo("test01")
                    .cpnIssNo(1L)
                    .ordNo("O3")
                    .build());
        });

    }

    @Test
    @DisplayName("쿠폰사용 - 프로모션 유효성체크")
    void useCoupon3(){

        // 쿠폰발급번호 14L은 존재하지 않음.
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            couponService.useCoupon(
                    CouponRequest.builder()
                    .cpnIssNo(14L)
                    .mbrNo("test01")
                    .ordNo("O3")
                    .build());
        });

    }

    @Test
    @DisplayName("쿠폰사용")
    void useCoupon4(){

        Long cpnIssNo = 2L;
        String mbrNo = "test01";
        String ordNo = "O3";

        couponService.useCoupon(
                CouponRequest.builder()
                        .cpnIssNo(cpnIssNo)
                        .mbrNo(mbrNo)
                        .ordNo(ordNo)
                        .build());

        CouponInfo couponInfo = getCouponInfo(cpnIssNo);

        Assertions.assertNotNull(couponInfo.getCpnUseDt());
        Assertions.assertEquals(ordNo, couponInfo.getOrdNo());


    }

    @Test
    @DisplayName("쿠폰사용 - 기사용쿠폰 체크")
    void useCoupon5(){

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            couponService.useCoupon(
                    CouponRequest.builder()
                    .cpnIssNo(2L)
                    .mbrNo("test01")
                    .ordNo("O3")
                    .build());
        });

    }

    @Test
    @DisplayName("쿠폰취소 - 복원 필수값체크")
    void cancelCoupon1(){

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            couponService.cancelUsingCoupon(
                    CouponRequest.builder()
                    .prmNo(2L)
//                .mbrNo("test01")
                    .cpnIssNo(8L)
                    .build());
        });

    }

    @Test
    @DisplayName("쿠폰 취소 - 복원 하는 case [프로모션 종료 전]")
    void cancelCouponRestore1(){

        Long cpnIssNo = 2L;

        couponService.cancelUsingCoupon(
                CouponRequest.builder()
                        .mbrNo("test01")
                        .cpnIssNo(cpnIssNo)
                        .build()
        );

        // id를 리턴하려했으나,, usegeneratedkeys와 selectkey를 함꼐쓰면 왜인지 return이 안되어서 일단 보류
//        CouponInfo couponInfo = getCouponInfo(model.getCpnIssNo());
//        Assertions.assertEquals(cpnIssNo, couponInfo.getOrgCpnIssNo());

    }

    @Test
    @DisplayName("쿠폰 취소 - 복원 안하는 case [프로모션 종료 후]")
    void cancelCouponRestore2(){

        String mbrNo = "test01";
        Long cpnIssNo = 1L;

        couponService.cancelUsingCoupon(
                CouponRequest.builder()
                        .mbrNo(mbrNo)
                        .cpnIssNo(cpnIssNo)
                        .build()
        );

    }

    CouponInfo getCouponInfo(Long cpnIssNo){
        return couponMapper.getCouponInfo(cpnIssNo);
    }

}