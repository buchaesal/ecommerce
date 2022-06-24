package com.plateer.ec1.claim.service;

import com.plateer.ec1.claim.vo.ClaimModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClaimDataManipulateService {

    public ClaimModel insertClaimData(ClaimModel claimModel) {
        log.info("클레임 데이터 insert - ClaimModel : {}", claimModel);
        return null;
    }
    public ClaimModel updateClaimData(ClaimModel claimModel) {
        log.info("클레임 데이터 update - ClaimModel : {}", claimModel);
        return null;
    }

//    public ClaimModel insertOrderCost(ClaimModel claimModel) {
//        return null;
//    }
//    public ClaimModel updateOrderCost(ClaimModel claimModel) {
//        return null;
//    }
//    public ClaimModel insertOrderBenefitRelation(ClaimModel claimModel) {
//        return null;
//    }
//    public ClaimModel updateOrderBenefitData(ClaimModel claimModel) {
//        return null;
//    }

}
