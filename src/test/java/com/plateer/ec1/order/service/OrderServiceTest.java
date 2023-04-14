package com.plateer.ec1.order.service;

import com.plateer.ec1.common.code.order.OPT0006;
import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.vo.*;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.Order;
import com.plateer.ec1.payment.vo.PaymentMethod;
import com.plateer.ec1.payment.vo.req.PaymentRequest;
import com.plateer.ec1.promotion.vo.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@SpringBootTest
class OrderServiceTest {

    OrderRequest orderRequest;
    @Autowired
    OrderService orderService;
    @Autowired
    OuterService outerService;

    @BeforeEach
    void init(){
        String ordNo = "O"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        orderRequest = new OrderRequest();
        orderRequest.setOrdNo(ordNo);
        orderRequest.setOrderType(OrderType.GENERAL);
        orderRequest.setSystemType(OrderSystemType.FO);
        orderRequest.setOrderBase(new OrderBase(ordNo,
                "test01",
                "10",
                "10",
                "최단비",
                "01047264128",
                "강서구",
                "아파트",
                "",
                "",
                ""));
        orderRequest.setProductList(Arrays.asList(
                OrderProduct.builder().orderCount(1).ordGoodsNo("P001").ordItemNo("1").build(),
                OrderProduct.builder().orderCount(1).ordGoodsNo("P001").ordItemNo("2").build()));
        orderRequest.setDeliveryList(Arrays.asList(
                OrderDelivery.builder().dvpSeq(1L).rmtiHpNo("01047264128").rmtiNm("최단비").rmtiAddr("배송지주소").groupDeliveryList(
                        Arrays.asList(
                                OrderGroupDelivery.builder().dvGrpNo(1L).feeList(Arrays.asList(DeliveryFee.builder().orgDvAmt(0L).dvBnfAmt(0L).dvAmtTpCd(OPT0006.DELIVERY_FEE.code).build())).productList(
                                        Arrays.asList(
                                                Product.builder().productNo("P001").productItemNo("1").build(),
                                                Product.builder().productNo("P001").productItemNo("2").build())).build()
                        )).build()
        ));
        orderRequest.setPaymentRequest(PaymentRequest.builder().order(Order.builder().ordNo(ordNo).build()).paymentMethodList(Arrays.asList(PaymentMethod.builder().payAmount(1000L).paymentType(PaymentType.POINT).build())).build());
    }

    @Test
    void logTest(){
        orderService.order(orderRequest);
    }

    @Test
    void test1(){
        outerService.saveTmpOrdBase();
    }

}