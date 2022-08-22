package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.creator.abstracts.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimVO;

public class WithdrawalReturnCreator extends ClaimCreator {

    public WithdrawalReturnCreator(ClaimDataManipulateService dataManipulateService) {
        super(dataManipulateService);
    }

    @Override
    public ClaimType getType() {
        return ClaimType.RW;
    }

    @Override
    protected ClaimDataModels makeClaimDataModels(ClaimVO claimVO) {
        return null;
    }

}
