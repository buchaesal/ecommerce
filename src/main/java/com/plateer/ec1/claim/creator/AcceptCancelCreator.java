package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.creator.abstracts.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimVO;

public class AcceptCancelCreator extends ClaimCreator {

    public AcceptCancelCreator(ClaimDataManipulateService dataManipulateService) {
        super(dataManipulateService);
    }

    @Override
    public ClaimType getType() {
        return ClaimType.MCA;
    }

    @Override
    protected ClaimDataModels makeClaimDataModels(ClaimVO claimVO) {
        return null;
    }

    @Override
    protected void updateOriginalOrderData(ClaimVO claimVO) {

    }

}
