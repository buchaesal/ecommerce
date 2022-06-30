package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.CalculationReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalculationMapper {

    List<Promotion> selectProductPromotionList(CalculationReqVO reqVO);

}
