package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.mapper.CalculationMapper;
import com.plateer.ec1.promotion.vo.CouponProduct;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.CartCouponRequest;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.CartCouponResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 주문서에서 조회
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CartCouponCalculator implements Calculator {

    private final CalculationMapper calculationMapper;

    public PromotionType getType(){
        return PromotionType.CC;
    }

    @Override
    public CartCouponResponse getCalculationData(PromotionRequest promotionRequest) {

        // 프로모션 리스트 조회
        CartCouponRequest request = new CartCouponRequest(promotionRequest);
        List<Promotion> promotionList = calculationMapper.selectCartPromotionList(request);

        List<CouponProduct> resultList = new ArrayList<>();

        promotionList.forEach(promotion -> {

            // 프로모션에 해당하는 대상 상품리스트 조회
            request.setPrmNo(promotion.getPrmNo());
            List<Product> productList = getApplyProductList(request);

            CouponProduct couponProduct = new CouponProduct(promotion, productList);

            // 장바구니쿠폰 [최소구매금액, 혜택가] 검증 및 계산
            couponProduct.validateCartCoupon(productList);
            resultList.add(couponProduct);

        });

        // valid true filter, 최대혜택 프로모션 YN set
        resultList.stream()
                .filter(CouponProduct::isValid)
                .max(Comparator.comparing(couponProduct -> couponProduct.getPromotion().getBenefitPrice()))
                .get()
                .getPromotion()
                .setMaxBenefitYn("Y");

        return new CartCouponResponse(resultList);

    }

    private List<Product> getApplyProductList(CartCouponRequest request){

        List<String> applyProductNoList = calculationMapper.selectApplyProductNoList(request);

        return request.getProductList().stream()
                .filter(product -> applyProductNoList.contains(product.getProductNo()))
                .collect(Collectors.toList());

    }

}
