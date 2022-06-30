package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0002;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CouponInfo {

    private String prmPriodCcd;
    private LocalDateTime prmStrtDt;
    private LocalDateTime prmEndDt;
    private int prmStdDd;
    private LocalDateTime sysRegDtime;

    public boolean isValid(){

        LocalDateTime nowToday = LocalDateTime.now();

        return PRM0002.PERIOD.code.equals(prmPriodCcd) ?
                prmStrtDt.isBefore(nowToday) && prmEndDt.isAfter(nowToday)
                : PRM0002.DAYS.code.equals(prmPriodCcd) ? sysRegDtime.isBefore(nowToday) && sysRegDtime.plusDays(prmStdDd).isAfter(nowToday)
                : false;

    }

}
