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
        List<CouponProduct> couponProductList = new ArrayList<>();

        // 조회해온 프로모션 리스트 반복문 시작
        calculationMapper.selectCartPromotionList(request).forEach(promotion -> {

            // 프로모션에 해당하는 대상 상품리스트 조회
            List<Product> productList = getApplyProductList(request, promotion);

            // [프로모션 - 상품리스트] 형태의 상품쿠폰 객체 생성
            CouponProduct couponProduct = new CouponProduct(promotion, productList);

            // 결과 리스트에 추가
            couponProductList.add(couponProduct);

        });

        // 장바구니쿠폰 응답객체 만들고 리턴
        return new CartCouponResponse(couponProductList);

    }

    private List<Product> getApplyProductList(CartCouponRequest request, Promotion promotion){

        request.setPrmNo(promotion.getPrmNo());
        List<String> applyProductNoList = calculationMapper.selectApplyProductNoList(request);

        return request.getProductList().stream()
                .filter(product -> applyProductNoList.contains(product.getProductNo()))
                .collect(Collectors.toList());

    }

}
