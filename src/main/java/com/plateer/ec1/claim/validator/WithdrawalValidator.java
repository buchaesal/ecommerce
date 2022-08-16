package com.plateer.ec1.claim.validator;

import com.plateer.ec1.claim.enums.ValidatorType;
import com.plateer.ec1.claim.vo.ClaimOrderInfo;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.common.code.order.OPT0004;

public class WithdrawalValidator extends ClaimValidator{

    @Override
    public ValidatorType getType() {
        return ValidatorType.WITHDRAWAL;
    }

    @Override
    public void validate(ClaimVO vo, ClaimOrderInfo orderInfo) {
        if(!orderInfo.getOrdPrgsScd().equals(OPT0004.EXCHANGE_ACCEPT.code)){
            throw new IllegalArgumentException("주문 상태가 올바르지 않습니다.");
        }
    }

}
