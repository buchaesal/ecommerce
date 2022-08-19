package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.creator.abstracts.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;

public class AcceptCancelCreator extends ClaimCreator {

    @Override
    public ClaimType getType() {
        return ClaimType.MCA;
    }

}
