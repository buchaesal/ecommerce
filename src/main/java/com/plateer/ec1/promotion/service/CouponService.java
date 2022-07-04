package com.plateer.ec1.promotion.service;

import com.plateer.ec1.promotion.mapper.CouponMapper;
import com.plateer.ec1.promotion.mapper.CouponTrxMapper;
import com.plateer.ec1.promotion.validation.Cancel;
import com.plateer.ec1.promotion.validation.Use;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import com.plateer.ec1.promotion.vo.res.CouponCountResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponMapper couponMapper;
    private final CouponTrxMapper couponTrxMapper;

    @Transactional
    public void downloadCoupon(CouponRequest request){

        // 다운로드 가능여부 체크
        // 다운로드 가능수량 조회 (조건 : 사용여부, 쿠폰 다운로드가능 시작/종료일)
        CouponCountResVO countRes = Optional.ofNullable(
                couponMapper.getAvailableCouponCountByPrmNo(request.makeAvailableCouponCountRequest()))
                .orElseThrow(() -> new IllegalArgumentException("프로모션 데이터를 찾을 수 없습니다."));

        // 다운로드 가능수량 검증
        countRes.validate();

        // 쿠폰발급회원 데이터 insert
        couponTrxMapper.insertCouponIssue(request.makeInsertCouponIssueModel());

    }

    @Validated(Use.class)
    @Transactional
    public void useCoupon(CouponRequest request){

        CouponInfo couponInfo = getCouponInfo(request);

        // 쿠폰사용 validate
        couponInfo.validateUsingCoupon();

        // 사용처리
        couponTrxMapper.updateUsingCoupon(request.makeUpdateUsingCouponModel());

    }

    @Validated(Cancel.class)
    @Transactional
    public void cancelUsingCoupon(CouponRequest request){

        CouponInfo couponInfo = getCouponInfo(request);
        request.setPrmNo(couponInfo.getPrmNo());

        // 프로모션 종료일시가 현재 이후일 때만 신규쿠폰 발급
        if(couponInfo.isValidPeriod()){
            couponTrxMapper.insertRestoreCoupon(request.makeInsertRestoreCouponModel());
        }

    }

    private CouponInfo getCouponInfo(CouponRequest request){
        return couponMapper.getCouponInfo(request.getCpnIssNo());
    }

}
