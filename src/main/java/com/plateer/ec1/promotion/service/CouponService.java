package com.plateer.ec1.promotion.service;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.mapper.CouponMapper;
import com.plateer.ec1.promotion.mapper.CouponTrxMapper;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.AvailableCouponCountReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponMapper couponMapper;
    private final CouponTrxMapper couponTrxMapper;

    @Transactional
    public void downloadCoupon(String memberNo, Promotion promotion){

        // 다운로드 가능여부 체크
        // (프로모션 종료일, 사용여부, 쿠폰 종료일, 쿠폰다운로드 (전체/개인별)가능수량)
        int count = couponMapper.getAvailableCouponCountByPrmNo(
                        AvailableCouponCountReqVO.builder()
                                .prmNo(promotion.getPromotionNo())
                                .mbrNo(memberNo)
                                .build());
        if(count < 1) throw new IllegalArgumentException("쿠폰 발행(다운로드) 불가");

        // 쿠폰발급회원 데이터 insert
        CcCpnIssueModel model = CcCpnIssueModel.builder()
                .prmNo(promotion.getPromotionNo())
                .mbrNo(memberNo)
                .build();
        couponTrxMapper.insertCouponIssue(model);

    }

    public void useCoupon(){

    }

}
