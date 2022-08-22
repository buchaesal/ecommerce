package com.plateer.ec1.claim.service;

import com.plateer.ec1.claim.mapper.ClaimTrxMapper;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimModel;
import com.plateer.ec1.common.model.order.OpClmInfoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClaimDataManipulateService {

    private final ClaimTrxMapper claimTrxMapper;

    public void manipulateClaimData(ClaimDataModels models) {

        models.getOrderClaimModelList().forEach(model -> claimTrxMapper.insertOrderClaimByCopy(model));
        models.getOrderCostModelList().forEach(model -> claimTrxMapper.insertOrderCostByCopy(model));
        models.getOrderBenefitRelationList().forEach(model -> claimTrxMapper.insertOrderBenefitRelationByCopy(model));
        models.getOrderBenefitList().forEach(model -> claimTrxMapper.updateOrderBenefit(model));

    }


}
