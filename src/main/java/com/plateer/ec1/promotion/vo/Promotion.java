package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0003;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Promotion {

    private Long prmNo;
    private String dcCcd; // 할인구분코드
    private double dcVal; // 할인구분코드에 따른 value

    private long benefitPrice; // 할인금액
    private String maxBenefitYn = "N";

    private long minPurAmt; // 최소구매금액
    private long maxDcAmt; // 최대할인금액

    private String cpnKindCd; // 쿠폰종류코드
    private Long cpnIssNo;
    private String degrCcd; // 차수구분코드
    private String applyYn; // 기적용쿠폰여부

    public void setBenefitPrice(long originalPrice){

        if(PRM0003.PRICE.code.equals(dcCcd)){
            benefitPrice = (long) dcVal;
        }else if(PRM0003.PERCENT.code.equals(dcCcd)){
            benefitPrice = (long) (originalPrice * (dcVal / 100));
        }

        // 최대할인금액 고려
        if(benefitPrice > maxDcAmt) {
            benefitPrice = maxDcAmt;
        }

    }

    public boolean validateMinPurAmt(long purAmt){
        return minPurAmt <= purAmt;
    }

}
