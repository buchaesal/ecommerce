package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.mapper.CalculationMapper;
import com.plateer.ec1.promotion.vo.ProductCouponVO;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.CalculationReqVO;
import com.plateer.ec1.promotion.vo.req.PromotionReqVO;
import com.plateer.ec1.promotion.vo.res.ProductCouponResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 다운로드 받은 쿠폰 리스트 중, 해당 상품에 적용가능한 프로모션 리스트 혜택계산 후 반환
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductCouponCalculator implements Calculator {

    private final CalculationMapper calculationMapper;

    public PromotionType getType(){
        return PromotionType.PRD_CUP;
    }

    @Override
    public ProductCouponResponseVO getCalculationData(PromotionReqVO reqVO) {

        CalculationReqVO request = new CalculationReqVO();
        request.setMbrNo(reqVO.getMemberNo());

        ProductCouponResponseVO response = new ProductCouponResponseVO();
        ProductCouponVO productCouponVO = new ProductCouponVO();
        List<ProductCouponVO> productCouponList = new ArrayList<>();

        reqVO.getProductList().forEach(product -> {

            // 상품 별 적용가능 프로모션 리스트 조회
            request.setProductNo(product.getProductNo());
            List<Promotion> promotionList = calculationMapper.selectProductPromotionList(request);

            // 혜택 적용
            promotionList.forEach(promotion -> promotion.setBenefitPrice(product.getProductAmt()));

            // 최대혜택 프로모션에 maxBenefitYn set
            promotionList.stream().max(Comparator.comparing(Promotion::getBenefitPrice)).get().setMaxBenefitYn("Y");

            productCouponVO.setProduct(product);
            productCouponVO.setPromotionList(promotionList);
            productCouponList.add(productCouponVO);

        });

        response.setProductCouponList(productCouponList);

        return response;

    }

}
