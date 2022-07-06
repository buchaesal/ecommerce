package com.plateer.ec1.promotion.service;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.CouponMapper;
import com.plateer.ec1.promotion.mapper.CouponTrxMapper;
import com.plateer.ec1.promotion.validation.Cancel;
import com.plateer.ec1.promotion.validation.Download;
import com.plateer.ec1.promotion.validation.Use;
import com.plateer.ec1.promotion.vo.CouponInfo;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import com.plateer.ec1.promotion.vo.res.CouponCountResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponMapper couponMapper;
    private final CouponTrxMapper couponTrxMapper;

    @Validated(Download.class)
    @Transactional
    public CcCpnIssueModel downloadCoupon(@Valid CouponRequest request){

        // 다운로드 가능여부 체크
        // 다운로드 가능수량 조회 (조건 : 사용여부, 쿠폰 다운로드가능 시작/종료일)
        CouponCountResVO countRes = Optional.ofNullable(
                couponMapper.getAvailableCouponCountByPrmNo(request.makeAvailableCouponCountRequest()))
                .orElseThrow(() -> new IllegalArgumentException("다운로드 가능한 쿠폰이 아닙니다."));

        // 다운로드 가능수량 검증
        countRes.validate();

        // 쿠폰발급회원 데이터 insert
        CcCpnIssueModel model = request.makeInsertCouponIssueModel();
        couponTrxMapper.insertCouponIssue(model);

        return model;

    }

    @Validated(Use.class)
    @Transactional
    public void useCoupon(@Valid CouponRequest request){

        CouponInfo couponInfo = Optional.ofNullable(getCouponInfo(request))
                .orElseThrow(() -> new IllegalArgumentException("사용하려는 쿠폰이 존재하지 않습니다."));

        // 쿠폰사용 validate
        couponInfo.validateUsingCoupon(request);

        // 사용처리
        couponTrxMapper.updateUsingCoupon(request.makeUpdateUsingCouponModel());

    }

    @Validated(Cancel.class)
    @Transactional
    public void cancelUsingCoupon(@Valid CouponRequest request){

        CouponInfo couponInfo = getCouponInfo(request);
        request.setPrmNo(couponInfo.getPrmNo());

        // 쿠폰 취소 validate
        couponInfo.validateCancelCoupon();

        // 프로모션 종료일시가 현재 이후일 때만 신규쿠폰 발급
        if(couponInfo.isValidPeriod()){
            couponTrxMapper.insertRestoreCoupon(request.makeInsertRestoreCouponModel());
        }

    }

    private CouponInfo getCouponInfo(CouponRequest request){
        return couponMapper.getCouponInfo(request.getCpnIssNo());
    }

}
