package com.plateer.ec1.claim.service;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.mapper.ClaimMapper;
import com.plateer.ec1.claim.mapper.ClaimTrxMapper;
import com.plateer.ec1.claim.vo.ClaimDataModels;
import com.plateer.ec1.claim.vo.ClaimModel;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.claim.vo.req.OrderClaimRequest;
import com.plateer.ec1.common.code.order.OPT0003;
import com.plateer.ec1.common.model.order.OpClmInfoModel;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClaimDataManipulateService {

    private final ClaimMapper claimMapper;
    private final ClaimTrxMapper claimTrxMapper;

    public void manipulateClaimData(ClaimDataModels models) {

        models.getOrderClaimModelList().forEach(model -> claimTrxMapper.insertOrderClaimByCopy(model));
        models.getOrderCostModelList().forEach(model -> claimTrxMapper.insertOrderCostByCopy(model));
        models.getOrderBenefitRelationList().forEach(model -> claimTrxMapper.insertOrderBenefitRelationByCopy(model));
        models.getOrderBenefitList().forEach(model -> claimTrxMapper.updateOrderBenefit(model));

    }

    public List<OpClmInfoModel> getOrderClaimList(ClaimVO claimVO){

        List<OpClmInfoModel> modelList = new ArrayList<>();
        // 반품철회일때만, 반품접수건을 가져온다.
        String ordClmTpCd = ClaimType.RW.getClaimCode().equals(claimVO.getClaimCode()) ? OPT0003.REFUND.code : OPT0003.ORDER.code;

        claimVO.getClamItemList().forEach(item -> {
            OrderClaimRequest request = OrderClaimRequest.builder()
                    .ordGoodsNo(item.getPdNo())
                    .ordItemNo(item.getItemNo())
                    .ordNo(claimVO.getOrdNo())
                    .ordClmTpCd(ordClmTpCd)
                    .build();
            modelList.add(claimMapper.getOrderClaim(request));
        });

        return modelList;

    }

}
