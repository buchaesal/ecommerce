package com.plateer.ec1.order.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderProduct {

    private String ordGoodsNo;
    private String ordItemNo;
    private String goodsSellTpCd; // 상품판매유형코드(PRD0001)
    private Long orderCount; // 주문수량
    private List<Benefit> benefitList;

}
