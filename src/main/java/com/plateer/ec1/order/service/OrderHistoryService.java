package com.plateer.ec1.order.service;

import com.google.gson.Gson;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.common.code.order.OPT0012;
import com.plateer.ec1.common.model.order.OpOrdClmMntLogModel;
import com.plateer.ec1.order.mapper.OrderTrxDao;
import com.plateer.ec1.order.vo.OrderRequest;
import com.plateer.ec1.order.vo.OrderVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderHistoryService {

    private final OrderTrxDao orderTrxDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insertOrderHistory(OrderRequest orderRequest){

        OpOrdClmMntLogModel model = OpOrdClmMntLogModel.builder()
                .ordNo(orderRequest.getOrdNo())
                .reqPram(new Gson().toJson(orderRequest))
                .build();

        orderTrxDao.insertOrderClaimMonitoringLog(model);

        return model.getLogSeq();

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insertClaimHistory(ClaimVO claimVO){

        OpOrdClmMntLogModel model = OpOrdClmMntLogModel.builder()
                .ordNo(claimVO.getOrdNo())
                .clmNo(claimVO.getClmNo())
                .reqPram(new Gson().toJson(claimVO))
                .build();

        orderTrxDao.insertOrderClaimMonitoringLog(model);

        return model.getLogSeq();

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateHistory(Long logSeq, Object targetVO, Exception ex){

        String procCcd = OPT0012.SUCCESS.code;

        if(Objects.nonNull(ex)){
            // 에러에 따라 procCcd set
        }

        orderTrxDao.updateOrderClaimMonitoringLog(OpOrdClmMntLogModel.builder()
                .insData(new Gson().toJson(targetVO))
                .logSeq(logSeq)
                .ProcCcd(procCcd)
                .build());

    }

    @Transactional
    public void insertOrderData(OrderVO orderVO){

        orderTrxDao.insertOrderBase(orderVO.getOpOrdBaseModel());
        orderVO.getOpGoodsInfoModelList().forEach(model -> orderTrxDao.insertOrderGoods(model));
        orderVO.getOpClmInfoModelList().forEach(model -> orderTrxDao.insertOrderClaim(model));
        orderVO.getOpDvpAreaInfoModelList().forEach(model -> orderTrxDao.insertOrderDeliveryArea(model));
        orderVO.getOpDvpInfoModelList().forEach(model -> orderTrxDao.insertOrderDeliveryInfo(model));
        orderVO.getOpOrdBnfRelInfoModelList().forEach(model -> orderTrxDao.insertOrderBenefitRelation(model));
        orderVO.getOpOrdBnfInfoModelList().forEach(model -> orderTrxDao.insertOrderBenefit(model));
        orderVO.getOpOrdCostInfoModelList().forEach(model -> orderTrxDao.insertOrderCost(model));

    }

    @Transactional
    public void errMethod(){
        if(true){
            throw new RuntimeException();
        }
    }

}
