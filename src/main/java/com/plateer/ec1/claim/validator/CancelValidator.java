package com.plateer.ec1.claim.validator;

import com.plateer.ec1.claim.enums.ValidatorType;
import org.springframework.stereotype.Component;

@Component
public class CancelValidator extends ClaimValidator{

    public ValidatorType getType() {
        return ValidatorType.CANCEL;
    }

}
