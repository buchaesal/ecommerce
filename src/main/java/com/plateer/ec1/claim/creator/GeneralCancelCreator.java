package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.creator.abstracts.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimVO;
import org.springframework.stereotype.Component;

@Component
public class GeneralCancelCreator extends ClaimCreator {

    public GeneralCancelCreator(ClaimDataManipulateService manipulateService){
        super(manipulateService);
    }

    @Override
    public ClaimType getType() {
        return ClaimType.GCC;
    }

    @Override
    protected ClaimDataModels makeClaimDataModels(ClaimVO claimVO) {
        return null;
    }

    @Override
    protected void updateOriginalOrderData(ClaimVO claimVO) {

    }


}
