package com.plateer.ec1.order.vo;

import com.plateer.ec1.common.model.order.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderVO {

    private OpOrdBase opOrdBase;
    private List<OpGoodsInfo> opGoodsInfoList;
    private List<OpClmInfo> opClmInfoList;
    private OpDvpAreaInfo opDvpAreaInfo;
    private List<OpDvpInfo> opDvpInfoList;
    private List<OpOrdCostInfo> opOrdCostInfo;
    private OpOrdBnfRelInfo opOrdBnfRelInfo;
    private OpOrdBnfInfo opOrdBnfInfo;

}
