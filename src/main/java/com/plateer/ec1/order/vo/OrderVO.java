package com.plateer.ec1.order.vo;

import com.plateer.ec1.common.model.order.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderVO {

    private OpOrdBaseModel opOrdBaseModel;
    private List<OpGoodsInfoModel> opGoodsInfoModelList;
    private List<OpClmInfoModel> opClmInfoModelList;
    private List<OpDvpAreaInfoModel> opDvpAreaInfoModelList;
    private List<OpDvpInfoModel> opDvpInfoModelList;
    private List<OpOrdCostInfoModel> opOrdCostInfoModelList;
    private List<OpOrdBnfRelInfoModel> opOrdBnfRelInfoModelList;
    private List<OpOrdBnfInfoModel> opOrdBnfInfoModelList;

}
