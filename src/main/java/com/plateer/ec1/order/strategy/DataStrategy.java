package com.plateer.ec1.order.strategy;

import com.plateer.ec1.common.code.delivery.DVP0001;
import com.plateer.ec1.common.code.order.OPT0003;
import com.plateer.ec1.common.code.order.OPT0004;
import com.plateer.ec1.common.code.order.OPT0005;
import com.plateer.ec1.common.code.order.OPT0014;
import com.plateer.ec1.common.code.promotion.PRM0004;
import com.plateer.ec1.common.code.promotion.PRM0012;
import com.plateer.ec1.common.model.order.*;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.mapper.OrderDao;
import com.plateer.ec1.order.vo.*;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.promotion.vo.Product;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DataStrategy {

    private final OrderDao orderDao;
    private Map<String, Long> ordSeqMap = new HashMap<>();

    protected DataStrategy(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public abstract OrderType getType();

    public OrderVO create(OrderRequest orderRequest){

        OrderVO orderVO = new OrderVO();

        // 주문기본
        setOrderBaseModel(orderRequest, orderVO);

        // 주문상품
        setOrderProductModel(orderRequest, orderVO);

        // 주문클레임
        setOrderClaimModel(orderRequest, orderVO);

        // 주문비용
        setOrderCostModel(orderRequest, orderVO);

        // 주문배송정보
        setOrderDeliveryModel(orderRequest, orderVO);

        // 주문배송지
        setOrderDeliveryAreaModel(orderRequest, orderVO);

        // 주문혜택, 주문혜택관계
        setOrderBenefit(orderRequest, orderVO);

        return orderVO;

    };

    public String getNewOrderBenefitNumber(){
        return orderDao.getOrderBenefitNumber();
    }

    public void setOrderBaseModel(OrderRequest orderRequest, OrderVO orderVO){
        OpOrdBaseModel opOrdBaseModel = new OpOrdBaseModel();
        BeanUtils.copyProperties(orderRequest.getOrderBase(), opOrdBaseModel);
        orderVO.setOpOrdBaseModel(opOrdBaseModel);
    }

    public void setOrderProductModel(OrderRequest orderRequest, OrderVO orderVO){

        List<OpGoodsInfoModel> opGoodsInfoModelList = new ArrayList<>();
        OpGoodsInfoModel opGoodsInfoModel = new OpGoodsInfoModel();

        orderRequest.getProductList().forEach((product) -> {

            BeanUtils.copyProperties(product, opGoodsInfoModel);
            opGoodsInfoModel.setOrdNo(orderRequest.getOrdNo());
            opGoodsInfoModelList.add(opGoodsInfoModel);

        });

        orderVO.setOpGoodsInfoModelList(opGoodsInfoModelList);

    }

    public void setOrderClaimModel(OrderRequest orderRequest, OrderVO orderVO){

        boolean hasVirtualAccount = orderRequest.getPaymentTypeList().contains(PaymentType.INICIS);

        List<OpClmInfoModel> opClmInfoModelList = new ArrayList<>();

        long seq = 1;
        for(OrderDelivery delivery : orderRequest.getDeliveryList()){
            for(OrderGroupDelivery groupDelivery : delivery.getGroupDeliveryList()){
                for(Product product : groupDelivery.getProductList()){
                    ordSeqMap.put(product.getProductNo() + product.getProductItemNo(), seq);
                    OpClmInfoModel opClmInfoModel = OpClmInfoModel.builder()
                            .ordNo(orderRequest.getOrdNo())
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
                            .ordPrgsScd(hasVirtualAccount ? OPT0004.ORDER_WAITING.code : OPT0004.ORDER_COMPLETE.code)
                            .ordClmAcptDtime(LocalDateTime.now())
                            .ordClmCmtDtime(hasVirtualAccount ? null : LocalDateTime.now())
                            .build();

                    opClmInfoModelList.add(opClmInfoModel);
                }
            }
        }

        orderVO.setOpClmInfoModelList(opClmInfoModelList);

    }

    public void setOrderCostModel(OrderRequest orderRequest, OrderVO orderVO){

        List<OpOrdCostInfoModel> opOrdCostInfoModelList = new ArrayList<>();

        for(OrderDelivery delivery : orderRequest.getDeliveryList()){
            for(OrderGroupDelivery groupDelivery : delivery.getGroupDeliveryList()){
                for(DeliveryFee deliveryFee : groupDelivery.getFeeList()){
                    OpOrdCostInfoModel opOrdCostInfoModel = getCommonCostInfoModel(orderRequest);
                    opOrdCostInfoModel.setDvGrpNo(groupDelivery.getDvGrpNo());
                    opOrdCostInfoModel.setDvAmtTpCd(deliveryFee.getDvAmtTpCd());
                    opOrdCostInfoModel.setOrgDvAmt(deliveryFee.getOrgDvAmt());
                    opOrdCostInfoModel.setDvBnfAmt(deliveryFee.getDvBnfAmt());
                    opOrdCostInfoModel.setAplyDvAmt(deliveryFee.getOrgDvAmt() - deliveryFee.getDvBnfAmt());
                    opOrdCostInfoModelList.add(opOrdCostInfoModel);
                }
            }
        }

        orderVO.setOpOrdCostInfoModelList(opOrdCostInfoModelList);

    };

    public void setOrderDeliveryModel(OrderRequest orderRequest, OrderVO orderVO){

        List<OpDvpInfoModel> opDvpInfoModelList = new ArrayList<>();

        long dvpSeq = 1;
        for(OrderDelivery delivery : orderRequest.getDeliveryList()){
            OpDvpInfoModel opDvpInfoModel = getCommonDvpInfoModel(orderRequest);
            opDvpInfoModel.setDvpSeq(dvpSeq++);
            for(OrderGroupDelivery groupDelivery : delivery.getGroupDeliveryList()){
                opDvpInfoModel.setDvGrpNo(groupDelivery.getDvGrpNo());
                opDvpInfoModelList.add(opDvpInfoModel);
            }
        }

        orderVO.setOpDvpInfoModelList(opDvpInfoModelList);

    }

    private OpDvpInfoModel getCommonDvpInfoModel(OrderRequest orderRequest){
        return OpDvpInfoModel.builder()
                .ordNo(orderRequest.getOrdNo())
                .dvMthdCd(DVP0001.DELIVERY.code)
                .build();
    }

    public void setOrderDeliveryAreaModel(OrderRequest orderRequest, OrderVO orderVO){

        List<OpDvpAreaInfoModel> dvpAreaInfoModelList = new ArrayList<>();

        for(OrderDelivery delivery : orderRequest.getDeliveryList()){
            OpDvpAreaInfoModel opDvpAreaInfoModel = new OpDvpAreaInfoModel();
            BeanUtils.copyProperties(delivery, opDvpAreaInfoModel);
            opDvpAreaInfoModel.setOrdNo(orderRequest.getOrdNo());
            dvpAreaInfoModelList.add(opDvpAreaInfoModel);
        }

        orderVO.setOpDvpAreaInfoModelList(dvpAreaInfoModelList);

    }

    public void setOrderBenefit(OrderRequest orderRequest, OrderVO orderVO){

        Map<String, Long> productAmtMap = new HashMap<>();
        List<OpOrdBnfInfoModel> bnfInfoList = new ArrayList<>();
        List<OpOrdBnfRelInfoModel> bnfRelInfoList = new ArrayList<>();

        long productBenefitSeq = 1;
        String mapKey;
        for(OrderProduct product : orderRequest.getProductList()){

            mapKey = product.getOrdGoodsNo() + product.getOrdItemNo();

            productAmtMap.put(mapKey,
                    product.getSellDcAmt() - product.getBenefitList().stream().mapToLong(benefit -> benefit.getOrdBnfAmt()).sum());

            OpOrdBnfRelInfoModel bnfRelInfo = getCommonBnfRelInfoModel(orderRequest);
            bnfRelInfo.setOrdSeq(ordSeqMap.get(mapKey));

            for(Benefit benefit : product.getBenefitList()){

                String ordBnfNo = getNewOrderBenefitNumber();
                OpOrdBnfInfoModel bnfInfo = getCommonBnfInfoModel();
                bnfInfo.setOrdBnfNo(ordBnfNo);
                bnfInfo.setPrmNo(benefit.getPrmNo());
                bnfInfo.setOrdBnfAmt(benefit.getOrdBnfAmt());
                bnfInfoList.add(bnfInfo);

                bnfRelInfo.setOrdBnfNo(ordBnfNo);
                bnfRelInfo.setProcSeq(productBenefitSeq++);
                bnfRelInfo.setAplyAmt(benefit.getOrdBnfAmt());
                bnfRelInfoList.add(bnfRelInfo);

            }
        }


        long cartBenefitSeq = 1;
        long payAmount = orderRequest.getPayAmount();
        for(OrderBenefit benefit : orderRequest.getOrderBenefitList()){

            String ordBnfNo = getNewOrderBenefitNumber();
            OpOrdBnfInfoModel bnfInfo = getCommonBnfInfoModel();
            bnfInfo.setOrdBnfNo(ordBnfNo);
            bnfInfo.setPrmNo(benefit.getPrmNo());
            bnfInfo.setOrdBnfAmt(benefit.getOrdBnfAmt());
            bnfInfoList.add(bnfInfo);

            for(Product product : benefit.getProductList()){
                mapKey = product.getProductNo() + product.getProductItemNo();
                OpOrdBnfRelInfoModel bnfRelInfo = getCommonBnfRelInfoModel(orderRequest);
                bnfRelInfo.setOrdSeq(ordSeqMap.get(mapKey));
                bnfRelInfo.setOrdBnfNo(ordBnfNo);
                bnfRelInfo.setProcSeq(cartBenefitSeq++);
                bnfRelInfo.setAplyAmt(benefit.getOrdBnfAmt() * (productAmtMap.get(mapKey) / payAmount));
                bnfRelInfoList.add(bnfRelInfo);
            }

        }

        orderVO.setOpOrdBnfInfoModelList(bnfInfoList);
        orderVO.setOpOrdBnfRelInfoModelList(bnfRelInfoList);

    }

    private OpOrdCostInfoModel getCommonCostInfoModel(OrderRequest orderRequest){
        return OpOrdCostInfoModel.builder()
                .ordNo(orderRequest.getOrdNo())
                .aplyCcd(OPT0005.APPLY.code)
                .build();
    }

    private OpOrdBnfRelInfoModel getCommonBnfRelInfoModel(OrderRequest orderRequest){
        return OpOrdBnfRelInfoModel.builder()
                .ordNo(orderRequest.getOrdNo())
                .procSeq(1L)
                .aplyCnclCcd(OPT0005.APPLY.code)
                .build();
    }

    private OpOrdBnfInfoModel getCommonBnfInfoModel(){
        return OpOrdBnfInfoModel.builder()
                .cpnKndCd(PRM0004.PRODUCT.code)
                .degrCcd(PRM0012.FIRST.code)
                .ordCnclBnfAmt(0L)
                .build();
    }

}
