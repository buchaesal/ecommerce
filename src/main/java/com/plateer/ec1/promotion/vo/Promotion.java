package com.plateer.ec1.promotion.vo;

import com.plateer.ec1.common.code.promotion.PRM0003;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promotion {

    private Long prmNo;
    private String dcCcd; // 할인구분코드
    private double dcVal; // 할인구분코드에 따른 value

    private long benefitPrice; // 할인금액
    private String maxBenefitYn = "N";

//    private String cpnKindCd;
//    private Long cpnIssNo;

    public void setBenefitPrice(long originalPrice){
        if(PRM0003.PRICE.code.equals(dcCcd)){
            this.benefitPrice = (long) dcVal;
        }else{
            this.benefitPrice = (long) (originalPrice * (dcVal / 100));
        }
    }

}
