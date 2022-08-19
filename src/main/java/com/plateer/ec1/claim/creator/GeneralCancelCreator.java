package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.creator.abstracts.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.vo.ClaimVO;

public class GeneralCancelCreator extends ClaimCreator {

    @Override
    public ClaimType getType() {
        return ClaimType.GCC;
    }

    @Override
    public void doCreate(ClaimVO claimVO) {

    }

}
