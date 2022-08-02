package com.plateer.ec1.order.strategy.impl;

import com.plateer.ec1.common.code.delivery.DVP0001;
import com.plateer.ec1.common.code.order.OPT0003;
import com.plateer.ec1.common.code.order.OPT0004;
import com.plateer.ec1.common.code.order.OPT0005;
import com.plateer.ec1.common.code.order.OPT0014;
import com.plateer.ec1.common.code.product.PRD0002;
import com.plateer.ec1.common.model.order.*;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.strategy.DataStrategy;
import com.plateer.ec1.order.vo.*;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.promotion.vo.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class GeneralDataStrategy implements DataStrategy {

    @Override
    public OrderType getType() {
        return OrderType.GENERAL;
    }

    @Override
    public OrderVO create(OrderRequest orderRequest) {

        String ordNo = orderRequest.getOrdNo();

        boolean isVirtualAccount = orderRequest.getPaymentRequest().getPayInfoList()
                        .stream()
                        .anyMatch((payInfo -> payInfo.getPaymentType() == PaymentType.INICIS));

        OrderVO orderVO = new OrderVO();

        // 주문기본
        OpOrdBase opOrdBase = new OpOrdBase();
        BeanUtils.copyProperties(orderRequest.getOrderBase(), opOrdBase);

        // 주문상품
        List<OpGoodsInfo> opGoodsInfoList = new ArrayList<>();
        orderRequest.getProductList().forEach((product) -> {

            OpGoodsInfo opGoodsInfo = OpGoodsInfo.builder()
                    .ordNo(ordNo)
                    .ordGoodsNo(product.getOrdGoodsNo())
                    .ordItemNo(product.getOrdItemNo())
                    .goodsSellTpCd(product.getGoodsSellTpCd())
                    .goodsDlvTpCd(PRD0002.GENERAL.code)
                    .sellAmt(product.getSellAmt())
                    .sellDcAmt(product.getSellDcAmt())
                    .build();

            opGoodsInfoList.add(opGoodsInfo);
        });
        orderVO.setOpGoodsInfoList(opGoodsInfoList);

        // 주문클레임, 주문배송정보, 주문비용 (합배송정보 단위로 반복탐색)
        List<OpClmInfo> opClmInfoList = new ArrayList<>();
        List<OpDvpInfo> opDvpInfoList = new ArrayList<>();
        List<OpOrdCostInfo> opOrdCostInfoList = new ArrayList<>();

        OpDvpInfo opDvpInfo = OpDvpInfo.builder()
                .ordNo(ordNo)
                .dvMthdCd(DVP0001.DELIVERY.code)
                .dvpSeq(1L)
                .build();

        OpOrdCostInfo opOrdCostInfo = OpOrdCostInfo.builder()
                .ordNo(ordNo)
                .aplyCcd(OPT0005.APPLY.code)
                .build();



        long seq = 1;
        for(OrderGroupDelivery groupDelivery : orderRequest.getDeliveryList().get(0).getGroupDeliveryList()){

            opDvpInfo.setDvGrpNo(groupDelivery.getDvGrpNo());
            opDvpInfoList.add(opDvpInfo);

            for(DeliveryFee deliveryFee : groupDelivery.getFeeList()){

            }

            for(Product product : groupDelivery.getProductList()){

                OpClmInfo opClmInfo = OpClmInfo.builder()
                        .ordSeq(seq++)
                        .procSeq(1L)
                        .ordGoodsNo(product.getProductNo())
                        .ordItemNo(product.getProductItemNo())
                        .ordClmTpCd(OPT0003.ORDER.code)
                        .dvRvtCcd(OPT0014.DELIVERY.code)
                        .ordCnt(product.getProductCnt())
                        .ordAmt(product.getProductAmt())
                        .cnclCnt(0L)
                        .rtgsCnt(0L)
                        .dvGrpNo(groupDelivery.getDvGrpNo())
                        .ordPrgsScd(isVirtualAccount ? OPT0004.ORDER_WAITING.code : OPT0004.ORDER_COMPLETE.code)
                        .ordClmAcptDtime(LocalDateTime.now())
                        .ordClmCmtDtime(isVirtualAccount ? null : LocalDateTime.now())
                        .build();

                opClmInfoList.add(opClmInfo);

            }
        }
        orderVO.setOpClmInfoList(opClmInfoList);
        orderVO.setOpDvpInfoList(opDvpInfoList);

        // 주문배송지
        OrderDelivery orderDelivery = orderRequest.getDeliveryList().get(0);
        OpDvpAreaInfo opDvpAreaInfo = new OpDvpAreaInfo();
        BeanUtils.copyProperties(orderDelivery, opDvpAreaInfo);
        opDvpAreaInfo.setOrdNo(ordNo);
        orderVO.setOpDvpAreaInfo(opDvpAreaInfo);




       return orderVO;

    }

}
