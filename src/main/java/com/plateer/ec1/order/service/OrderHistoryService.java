package com.plateer.ec1.order.service;

import com.google.gson.Gson;
import com.plateer.ec1.common.code.order.OPT0012;
import com.plateer.ec1.common.model.order.OpOrdClmMntLogModel;
import com.plateer.ec1.order.mapper.OrderTrxDao;
import com.plateer.ec1.order.vo.OrderBase;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {

    private final OrderTrxDao orderTrxDao;

    public Long insertOrderHistory(OrderRequest orderRequest){

        OpOrdClmMntLogModel model = OpOrdClmMntLogModel.builder()
                .ordNo(orderRequest.getOrdNo())
                .reqPram(new Gson().toJson(orderRequest))
                .build();
        orderTrxDao.insertOrderClaimMonitoringLog(model);

        return model.getLogSeq();

    }

    public void updateOrderHistory(Long logSeq, OrderVO orderVO, Exception ex){

        String procCcd = OPT0012.SUCCESS.code;

        if(Objects.nonNull(ex)){
            // 에러에 따라 procCcd set
        }

        orderTrxDao.updateOrderClaimMonitoringLog(OpOrdClmMntLogModel.builder()
                .insData(new Gson().toJson(orderVO))
                .logSeq(logSeq)
                .ProcCcd(procCcd)
                .build());

    }

}
