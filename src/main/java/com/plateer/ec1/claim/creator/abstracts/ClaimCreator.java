package com.plateer.ec1.claim.creator.abstracts;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.vo.ClaimVO;

public abstract class ClaimCreator {

    abstract public ClaimType getType();

    abstract public void doCreate(ClaimVO claimVO);

}
