package com.plateer.ec1.promotion;

import com.plateer.ec1.promotion.calculator.impl.ProductCouponCalculator;
import com.plateer.ec1.promotion.vo.Product;
import com.plateer.ec1.promotion.vo.req.PromotionReqVO;
import com.plateer.ec1.promotion.vo.res.ProductCouponResponseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorTest {

    @Autowired
    ProductCouponCalculator productCouponCalculator;

    @Test
    void getProductCouponCalculationData() {

        Product product = Product.builder()
                .productNo("P007")
                .productAmt(50000).build();

        Product product1 = Product.builder()
                .productNo("P001")
                .productCnt(1)
                .productAmt(29000)
                .build();
//
//        Product product2 = Product.builder()
//                .productNo("P002")
//                .productCnt(2)
//                .productAmt(10250)
//                .build();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.add(product1);

        PromotionReqVO request = PromotionReqVO.builder()
                .memberNo("test01")
                .productList(productList)
                .build();

        ProductCouponResponseVO result = productCouponCalculator.getCalculationData(request);
        System.out.println(result.toString());

    }
}