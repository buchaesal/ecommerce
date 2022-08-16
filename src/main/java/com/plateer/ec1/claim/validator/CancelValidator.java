package com.plateer.ec1.claim.validator;

import com.plateer.ec1.claim.enums.ValidatorType;
import com.plateer.ec1.claim.vo.ClaimOrderInfo;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.common.code.order.OPT0004;
import org.springframework.stereotype.Component;

@Component
public class CancelValidator extends ClaimValidator{

    public ValidatorType getType() {
        return ValidatorType.CANCEL;
    }

    @Override
    public void validate(ClaimVO vo, ClaimOrderInfo orderInfo) {
        String ordPrgsScd = orderInfo.getOrdPrgsScd();
        if(!(ordPrgsScd.equals(OPT0004.ORDER_WAITING.code) || ordPrgsScd.equals(OPT0004.ORDER_COMPLETE.code))){
            throw new IllegalArgumentException("주문 상태가 올바르지 않습니다.");
        }
    }

}
