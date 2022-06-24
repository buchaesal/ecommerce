package com.plateer.ec1.promotion.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String productNo; // 상품번호
    private int productAmt; // 혜택가가 있을경우 혜택가, 없을경우 판매가
    private int productCnt; // 수량
    private String productItemNo; // 단품번호
}
