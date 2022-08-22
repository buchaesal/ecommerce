package com.plateer.ec1.claim.mapper;

import com.plateer.ec1.common.model.order.OpClmInfoModel;
import com.plateer.ec1.common.model.order.OpOrdBnfInfoModel;
import com.plateer.ec1.common.model.order.OpOrdBnfRelInfoModel;
import com.plateer.ec1.common.model.order.OpOrdCostInfoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClaimTrxMapper {
    void insertOrderClaimByCopy(OpClmInfoModel model);
    void insertOrderCostByCopy(OpOrdCostInfoModel model);
    void insertOrderBenefitRelationByCopy(OpOrdBnfRelInfoModel model);
    void updateOrderBenefit(OpOrdBnfInfoModel model);

}
