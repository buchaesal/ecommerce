package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.calculator.impl.CartCouponCalculator;
import com.plateer.ec1.promotion.calculator.impl.ProductCouponCalculator;
import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.req.PromotionRequest;
import com.plateer.ec1.promotion.vo.res.CartCouponResponse;
import com.plateer.ec1.promotion.vo.res.ProductCouponResponse;
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

    @Test
    @DisplayName("주문1")
    void order1CalculationTest() {

        Product product = Product.builder()
                .productNo("P001")
                .productItemNo("1")
                .productAmt(29000)
                .build();

        Product product1 = Product.builder()
                .productNo("P002")
                .productItemNo("1")
                .productAmt(10250)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);

        PromotionRequest request = new PromotionRequest();
        request.setMbrNo("test01");
        request.setProductList(productList);

        ProductCouponResponse result = productCouponCalculator.getCalculationData(request);
        System.out.println(result.toString());

    }

    @Test
    void getCartCouponData(){
        Product product = Product.builder()
                .productNo("P007")
                .productCnt(1)
                .productAmt(50000).build();

        Product product1 = Product.builder()
                .productNo("P001")
                .productCnt(1)
                .productAmt(29000)
                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);

        PromotionRequest request = new PromotionRequest();
        request.setMbrNo("test01");
        request.setProductList(productList);

        cartCouponCalculator.getCalculationData(request);
    }

    @Test
    void productCouponCalculate(){
        Product product1 = new Product("P001", 29000L, 1, "1", 1L,1L);
        Product product2 = new Product("P001", 29000L, 2, "2", null,null);
        Product product3 = new Product("P002", 10250L, 2, "1", null,null);
        Product product4 = new Product("P002", 29000L, 1, "2", null,null);
        Product product5 = new Product("P005", 9000L, 1, "1", null,null);
        Product product6 = new Product("P005", 9000L, 1, "2", null,null);
        Product product7 = new Product("P005", 9000L, 3, "3", null,null);
        Product product8 = new Product("P006", 140000L, 1, "0", null,null);
        Product product9 = new Product("P007", 140000L, 1, "1", null,null);
        Product product10 = new Product("P007", 140000L, 2, "2", null,null);
        Product product11 = new Product("P007", 140000L, 1, "3", null,null);

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
        request.setMbrNo("test01");
        request.setProductList(productList);
        productCouponCalculator.getCalculationData(request);

    }

}