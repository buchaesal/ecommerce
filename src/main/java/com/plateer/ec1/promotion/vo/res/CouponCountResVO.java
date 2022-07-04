package com.plateer.ec1.promotion.vo.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponCountResVO {

    private long dwlPsbCnt;
    private long psnDwlPsbCnt;
    private long usedCnt;
    private long psnUsedCnt;

    public void validate(){

        // 총 다운로드 가능수량, 개인별 다운로드 가능수량 모두 0일때 무제한 다운로드 가능.
        if(getDwlPsbCnt() != 0 && getPsnDwlPsbCnt() != 0){
            if(getDwlPsbCnt() <= getUsedCnt()) throw new IllegalArgumentException("쿠폰 다운로드 가능 횟수 초과");
            if(getPsnDwlPsbCnt() <= getPsnUsedCnt()) throw new IllegalArgumentException("회원당 쿠폰 다운로드 가능 횟수 초과");
        }

    }

}
