package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.mapper.CalculationMapper;
import com.plateer.ec1.promotion.vo.ProductCoupon;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.ProductCouponRequest;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.ProductCouponResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        return PromotionType.PC;
    }

    @Override
    public ProductCouponResponseVO getCalculationData(PromotionRequest promotionRequest) {

        ProductCouponRequest request = new ProductCouponRequest(promotionRequest);
        List<ProductCoupon> productCouponList = new ArrayList<>();

        promotionRequest.getProductList().forEach(product -> {

            // 상품 별 적용가능 프로모션 리스트 조회
            request.setProductInfo(product);
            List<Promotion> promotionList = calculationMapper.selectProductPromotionList(request);

            ProductCoupon productCoupon = new ProductCoupon(product);
            productCoupon.setPromotionListWithBenefit(promotionList);
            productCouponList.add(productCoupon);

        });

        ProductCouponResponseVO response = new ProductCouponResponseVO();
        response.setProductCouponList(productCouponList);

        return response;

    }

}
