package com.plateer.ec1.order.mapper;

import com.plateer.ec1.common.model.order.OpOrdClmMntLogModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderTrxMapper {

    void insertOrderClaimMonitoringLog(OpOrdClmMntLogModel model);
    void updateOrderClaimMonitoringLog(OpOrdClmMntLogModel model);

}
