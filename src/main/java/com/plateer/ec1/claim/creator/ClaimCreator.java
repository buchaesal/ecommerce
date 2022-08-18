package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.vo.ClaimVO;

public abstract class ClaimCreator {

    abstract public ClaimType getType();

    public void doCreate(ClaimVO claimVO){

        String claimCode = ClaimType.findClaimType(claimVO.getClaimCode(), claimVO.getProductTypeCode()).getClaimCode();

    }

}
