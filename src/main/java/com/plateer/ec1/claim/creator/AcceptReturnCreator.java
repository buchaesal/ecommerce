package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.creator.abstracts.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.common.model.order.OpClmInfoModel;

import java.util.ArrayList;
import java.util.List;

public class AcceptReturnCreator extends ClaimCreator {

    public AcceptReturnCreator(ClaimDataManipulateService dataManipulateService) {
        super(dataManipulateService);
    }

    @Override
    public ClaimType getType() {
        return ClaimType.RA;
    }

    @Override
    protected ClaimDataModels makeClaimDataModels(ClaimVO claimVO) {

        List<OpClmInfoModel> orderClaimModelList = new ArrayList<>();

        // 주문클레임 model
        dataManipulateService.getOrderClaimList(claimVO).forEach(orderClaim -> {

            orderClaimModelList.add(OpClmInfoModel.builder().build());
        });

        return null;

    }

}
