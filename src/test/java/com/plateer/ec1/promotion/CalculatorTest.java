package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.calculator.impl.CartCouponCalculator;
import com.plateer.ec1.promotion.calculator.impl.ProductCouponCalculator;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.Promotion;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.CartCouponResponse;
import com.plateer.ec1.promotion.vo.res.ProductCouponResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CalculatorTest {

    @Autowired
    ProductCouponCalculator productCouponCalculator;

    @Autowired
    CartCouponCalculator cartCouponCalculator;

    PromotionRequest getOrder1PromotionRequest(){

        Product product = Product.builder()
                .productNo("P001")
                .productItemNo("1")
                .productAmt(29000)
                .build();

        Product product1 = Product.builder()
                .productNo("P002")
                .productItemNo("1")
                .productAmt(10250)
                .productCnt(3L)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);

        PromotionRequest request = new PromotionRequest();
        request.setMbrNo("test01");
        request.setProductList(productList);

        return request;
    }

    PromotionRequest getOrder2PromotionRequest(){

        Product product1 = new Product("P001", 29000L, 1L, "1", 1L,1L);
        Product product2 = new Product("P001", 29000L, 2L, "2", null,null);
        Product product3 = new Product("P002", 10250L, 2L, "1", null,null);
        Product product4 = new Product("P002", 10250L, 1L, "2", null,null);
        Product product5 = new Product("P005", 9000L, 1L, "1", null,null);
        Product product6 = new Product("P005", 9000L, 1L, "2", null,null);
        Product product7 = new Product("P005", 9000L, 3L, "3", null,null);
        Product product8 = new Product("P006", 140000L, 1L, "0", null,null);
        Product product9 = new Product("P007", 24000L, 1L, "1", null,null);
        Product product10 = new Product("P007", 24000L, 2L, "2", null,null);
        Product product11 = new Product("P007", 24000L, 1L, "3", null,null);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
        productList.add(product6);
        productList.add(product7);
        productList.add(product8);
        productList.add(product9);
        productList.add(product10);
        productList.add(product11);

        PromotionRequest request = new PromotionRequest();
        request.setMbrNo("test02");
        request.setProductList(productList);

        return request;
    }

    @Test
    @DisplayName("주문1 - 상품쿠폰")
    void productCouponOrder1() {

        ProductCouponResponse result = productCouponCalculator.getCalculationData(getOrder1PromotionRequest());

        result.getProductCouponList().forEach((productCoupon -> {
            productCoupon.getPromotionList().forEach((promotion)->{
                Assertions.assertEquals(promotion.getBenefitPrice(), 1000);
                Assertions.assertEquals(promotion.getMaxBenefitYn(), "Y");
            });
        }));

    }

    @Test
    @DisplayName("주문1 - 장바구니쿠폰")
   void cartCouponOrder1(){

        CartCouponResponse result = cartCouponCalculator.getCalculationData(getOrder1PromotionRequest());

        result.getCouponProductList().forEach((couponProduct -> {
            Promotion promotion = couponProduct.getPromotion();
            Assertions.assertEquals(promotion.getBenefitPrice(), 1000);
            Assertions.assertEquals(promotion.getMaxBenefitYn(), "Y");
        }));

    }

    @Test
    @DisplayName("주문2 - 상품쿠폰")
    void productCouponOrder2(){
        CartCouponResponse result = cartCouponCalculator.getCalculationData(getOrder2PromotionRequest());


    }

    @Test
    @DisplayName("주문2 - 장바구니쿠폰")
    void cartCouponOrder2(){

    }

}