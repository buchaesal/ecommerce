package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0002;
import com.plateer.ec1.promotion.vo.req.CouponRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CouponInfo {

    private Long prmNo;
    private String prmPriodCcd;
    private LocalDateTime prmStrtDt;
    private LocalDateTime prmEndDt;
    private int prmStdDd;
    private String mbrNo;
    private String ordNo;
    private LocalDateTime sysRegDtime;
    private LocalDateTime cpnUseDt;
    private Long orgCpnIssNo;
    private int notUsedCouponCnt; // 재발급한 미사용 쿠폰 갯수

    public boolean isValidPeriod(){

        LocalDateTime nowToday = LocalDateTime.now();

        return PRM0002.PERIOD.code.equals(prmPriodCcd) ?
                prmStrtDt.isBefore(nowToday) && prmEndDt.isAfter(nowToday)
                : PRM0002.DAYS.code.equals(prmPriodCcd) ? sysRegDtime.isBefore(nowToday) && sysRegDtime.plusDays(prmStdDd).isAfter(nowToday)
                : false;

    }

    public boolean isValidMbrNo(String paramMbrNo){
        return mbrNo.equals(paramMbrNo);
    }

    public boolean isUsedCoupon(){
        return cpnUseDt != null;
    }

    public void validateUsingCoupon(CouponRequest request){
        if(isUsedCoupon()){
            throw new IllegalArgumentException("이미 사용된 쿠폰입니다.");
        }
        if(!isValidPeriod()){
            throw new IllegalArgumentException("프로모션 기간이 아닙니다.");
        }
        if(!isValidMbrNo(request.getMbrNo())){
            throw new IllegalArgumentException("쿠폰 사용 회원을 확인해주세요.");
        }
    }

    public void validateCancelCoupon(){
        if(!isUsedCoupon()){
            throw new IllegalArgumentException("사용하지 않은 쿠폰입니다.");
        }
        if(hasNotUsedRestoredCoupon()){
            throw new IllegalArgumentException("이미 취소 된 쿠폰입니다.");
        }
    }

    private boolean hasNotUsedRestoredCoupon(){
        return notUsedCouponCnt > 0;
    }

}
