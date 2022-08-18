package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.enums.ClaimType;

public class AcceptReturnCreator extends ClaimCreator{

    @Override
    public ClaimType getType() {
        return ClaimType.RA;
    }

}
