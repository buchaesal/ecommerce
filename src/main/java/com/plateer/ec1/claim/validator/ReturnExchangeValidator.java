package com.plateer.ec1.claim.validator;

import com.plateer.ec1.claim.enums.ValidatorType;
import com.plateer.ec1.claim.vo.ClaimVO;

public class ReturnExchangeValidator extends ClaimValidator{

    @Override
    public ValidatorType getType() {
        return ValidatorType.RETURN_EXCHANGE;
    }

    @Override
    public void validate(ClaimVO vo) {

    }

}
