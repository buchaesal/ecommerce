package com.plateer.ec1.promotion.calculator.impl;

import com.plateer.ec1.promotion.calculator.Calculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.mapper.CalculationMapper;
import com.plateer.ec1.promotion.utils.PromotionUtil;
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
    public CartCouponResponse getCalculationData(PromotionRequest reqVO) {

        List<CouponProduct> resultList = new ArrayList<>();

        // 프로모션 리스트 조회
        CartCouponRequest request = new CartCouponRequest(reqVO);
        List<Promotion> promotionList = calculationMapper.selectCartPromotionList(request);

        promotionList.forEach(promotion -> {

            // 프로모션에 해당하는 대상 상품리스트 조회
            request.setPrmNo(promotion.getPrmNo());
            List<String> applyProductNoList = calculationMapper.selectApplyProductNoList(request);

            List<Product> productList = reqVO.getProductList().stream()
                    .filter(product -> applyProductNoList.contains(product.getProductNo()))
                    .collect(Collectors.toList());

            promotion.setProductList(productList);

            // 장바구니 상품 총합 계산 (가격*수량)
            long cartSum = productList.stream()
                    .map(product -> product.getProductAmt() * product.getProductCnt())
                    .mapToLong(i -> i).sum();

            // 최소구매금액 검증된 프로모션만 valid true, 혜택가 set
            if(promotion.validateMinPurAmt(cartSum)){
                promotion.setBenefitPrice(cartSum);
                promotion.setValid(true);
            }

        });

        // valid true인 프로모션만 객체생성하여 리스트에 추가
        promotionList = promotionList.stream().filter(promotion -> promotion.isValid()).collect(Collectors.toList());

        // 최대혜택 프로모션 YN set
        PromotionUtil.setMaxBenefitYn(promotionList);

        promotionList.forEach(promotion -> {
            CouponProduct couponProduct = new CouponProduct(promotion);
            resultList.add(couponProduct);
        });

        return new CartCouponResponse(resultList);

    }

}
