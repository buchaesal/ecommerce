package com.plateer.ec1.claim.validator;

import com.plateer.ec1.claim.enums.ValidatorType;
import com.plateer.ec1.claim.vo.ClaimVO;

public abstract class ClaimValidator {

    public abstract ValidatorType getType();

    public void validate(ClaimVO vo){
    };

}
