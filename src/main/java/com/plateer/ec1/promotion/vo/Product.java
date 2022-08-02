package com.plateer.ec1.promotion.vo;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Product {
    @NotEmpty
    private String productNo; // 상품번호
    private long productAmt; // 가격
    private Long productCnt; // 수량
    private String productItemNo; // 단품번호
    private Long prmNo;
    private Long cpnIssNo;
}
