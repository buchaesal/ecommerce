package com.plateer.ec1.claim.validator;

import com.plateer.ec1.claim.enums.ValidatorType;

public class ReturnExchangeValidator extends ClaimValidator{

    @Override
    public ValidatorType getType() {
        return ValidatorType.RETURN_EXCHANGE;
    }
}
