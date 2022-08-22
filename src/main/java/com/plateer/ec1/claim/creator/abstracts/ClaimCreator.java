package com.plateer.ec1.claim.creator.abstracts;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public abstract class ClaimCreator {

    private final ClaimDataManipulateService dataManipulateService;

    abstract public ClaimType getType();

    protected abstract ClaimDataModels makeClaimDataModels(ClaimVO claimVO);

    void execute(ClaimVO claimVO){

        ClaimDataModels models = makeClaimDataModels(claimVO);
        dataManipulateService.manipulateClaimData(models);

    }

}
