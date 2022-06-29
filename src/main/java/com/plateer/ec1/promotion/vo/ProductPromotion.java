package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.PRM0002;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductPromotion {

    private String prmPriodCcd;
    private LocalDateTime prmStrtDt;
    private LocalDateTime prmEndDt;
    private int prmStdDd;
    private String dcCcd;
    private long dcVal;
    private String aplyTgtNo;
    private LocalDateTime sysRegDtime; // 쿠폰다운로드일

    public boolean isValid(){
        LocalDateTime todayNow = LocalDateTime.now();
        if(PRM0002.PERIOD.code.equals(prmPriodCcd)){
            // 기간
            return prmStrtDt.isBefore(todayNow) && prmEndDt.isAfter(todayNow);
        }else{
            // 기준일
            return todayNow.isBefore(sysRegDtime.plusDays(prmStdDd));
        }
    }

}
