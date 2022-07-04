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
    private LocalDateTime sysRegDtime;

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

    public void validateUsingCoupon(CouponRequest request){
        if(!isValidPeriod()){
            throw new IllegalArgumentException("프로모션 기간이 아닙니다.");
        }
        if(!isValidMbrNo(request.getMbrNo())){
            throw new IllegalArgumentException("쿠폰 사용 회원을 확인해주세요.");
        }
    }



}
