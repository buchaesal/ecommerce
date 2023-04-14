package com.plateer.ec1.order.mapper;

import com.plateer.ec1.common.model.order.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTrxDao {

    void insertOrderClaimMonitoringLog(OpOrdClmMntLogModel model);
    void updateOrderClaimMonitoringLog(OpOrdClmMntLogModel model);
    void insertOrderBase(OpOrdBaseModel model);
    void insertOrderGoods(OpGoodsInfoModel model);
    void insertOrderClaim(OpClmInfoModel model);
    void insertOrderDeliveryArea(OpDvpAreaInfoModel model);
    void insertOrderDeliveryInfo(OpDvpInfoModel model);
    void insertOrderBenefitRelation(OpOrdBnfRelInfoModel model);
    void insertOrderBenefit(OpOrdBnfInfoModel model);
    void insertOrderCost(OpOrdCostInfoModel model);
    void insertTmpOrdBase();
    String getTmpOrdBaseSeq();

}
