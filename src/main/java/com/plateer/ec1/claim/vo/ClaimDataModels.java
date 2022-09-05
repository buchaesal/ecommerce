package com.plateer.ec1.claim.vo;

import com.plateer.ec1.common.model.order.OpClmInfoModel;
import com.plateer.ec1.common.model.order.OpOrdBnfInfoModel;
import com.plateer.ec1.common.model.order.OpOrdBnfRelInfoModel;
import com.plateer.ec1.common.model.order.OpOrdCostInfoModel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ClaimDataModels {

    private List<OpClmInfoModel> orderClaimModelList;
    private List<OpOrdCostInfoModel> orderCostModelList;
    private List<OpOrdBnfRelInfoModel> orderBenefitRelationList;
    private List<OpOrdBnfInfoModel> orderBenefitList;

}
