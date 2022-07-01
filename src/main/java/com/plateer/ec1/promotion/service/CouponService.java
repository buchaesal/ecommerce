package com.plateer.ec1.promotion.service;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.CouponMapper;
import com.plateer.ec1.promotion.mapper.CouponTrxMapper;
import com.plateer.ec1.promotion.vo.req.AvailableCouponCountRequest;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import com.plateer.ec1.promotion.vo.res.CouponCountResVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponMapper couponMapper;
    private final CouponTrxMapper couponTrxMapper;

    @Transactional
    public void downloadCoupon(CouponRequest reqVO){

        // 다운로드 가능여부 체크
        // 다운로드 가능수량 조회 (조건 : 사용여부, 쿠폰 다운로드가능 시작/종료일)
        CouponCountResVO countRes = Optional.ofNullable(couponMapper.getAvailableCouponCountByPrmNo(
                AvailableCouponCountRequest.builder()
                        .prmNo(reqVO.getPrmNo())
                        .mbrNo(reqVO.getMbrNo())
                        .build())).orElseThrow(() -> new IllegalArgumentException("프로모션 데이터를 찾을 수 없습니다."));

        // 다운로드 가능수량 검증
        validateCouponDownloadCount(countRes);

        // 쿠폰발급회원 데이터 insert
        couponTrxMapper.insertCouponIssue(
                CcCpnIssueModel.builder()
                .prmNo(reqVO.getPrmNo())
                .mbrNo(reqVO.getMbrNo())
                .build());

    }

    private void validateCouponDownloadCount(CouponCountResVO countRes){

        // 총 다운로드 가능수량, 개인별 다운로드 가능수량 모두 0일때 무제한 다운로드 가능.
        if(countRes.getDwlPsbCnt() != 0 && countRes.getPsnDwlPsbCnt() != 0){
            if(countRes.getDwlPsbCnt() <= countRes.getUsedCnt()) throw new IllegalArgumentException("쿠폰 다운로드 가능 횟수 초과");
            if(countRes.getPsnDwlPsbCnt() <= countRes.getPsnUsedCnt()) throw new IllegalArgumentException("회원당 쿠폰 다운로드 가능 횟수 초과");
        }

    }

    @Transactional
    public void useCoupon(CouponRequest reqVO){

        // 프로모션 기간검증
        if(!isValidCoupon(reqVO)){
            throw new IllegalArgumentException("프로모션 기간이 아닙니다.");
        }

        // 사용처리
        couponTrxMapper.updateUsingCoupon(
                CcCpnIssueModel.builder()
                        .cpnIssNo(reqVO.getCpnIssNo())
                        .ordNo(reqVO.getOrdNo())
                        .build());

    }

    @Transactional
    public void cancelUsingCoupon(CouponRequest reqVO){

        // 프로모션 종료일시가 현재 이후일 때만 신규쿠폰 발급
        if(isValidCoupon(reqVO)){
            couponTrxMapper.insertRestoreCoupon(
                    CcCpnIssueModel.builder()
                    .mbrNo(reqVO.getMbrNo())
                    .prmNo(reqVO.getPrmNo())
                    .cpnIssNo(reqVO.getCpnIssNo())
                    .build()
            );
        }

    }

    private boolean isValidCoupon(CouponRequest reqVO){
        return couponMapper.getCouponInfo(reqVO.getCpnIssNo()).isValid();
    }

}
