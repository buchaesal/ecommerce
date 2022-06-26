package com.plateer.ec1.claim.factory;

import com.plateer.ec1.claim.creator.ClaimCreator;
import com.plateer.ec1.claim.creator.GeneralClaimCreator;
import com.plateer.ec1.claim.vo.ClaimVO;
import org.springframework.stereotype.Component;

@Component
public class CreatorFactory {

    public ClaimCreator findCreator(ClaimVO claimVO){
        return new GeneralClaimCreator();
    }



}
