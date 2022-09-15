package com.plateer.ec1.claim.creator;

import com.plateer.ec1.claim.creator.abstracts.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.common.code.order.OPT0003;
import com.plateer.ec1.common.model.order.OpClmInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 반품철회
 */
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

        List<OpClmInfoModel> orderClaimModelList = new ArrayList<>();

        // 주문클레임 model
        dataManipulateService.getOrderClaimList(claimVO).forEach(orderClaim -> {
            orderClaim.setProcSeq(orderClaim.getProcSeq()+1);
            orderClaim.setOrdClmTpCd(OPT0003.REFUND_CANCEL.code);


            orderClaimModelList.add(orderClaim);
        });

        return null;

    }

    @Override
    protected void updateOriginalOrderData(ClaimVO claimVO) {

    }

}
