package com.plateer.ec1.promotion.mapper;

import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.CartCouponRequest;
import com.plateer.ec1.promotion.vo.req.ProductCouponRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalculationMapper {

    List<Promotion> selectProductPromotionList(ProductCouponRequest reqVO);
    List<Promotion> selectCartPromotionList(CartCouponRequest reqVO);
    List<String> selectApplyProductNoList(CartCouponRequest reqVO);

}
