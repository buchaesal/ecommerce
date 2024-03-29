package com.plateer.ec1.order.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
@ToString
public class OrderProduct {

    @NotEmpty
    private String ordGoodsNo;
    @NotEmpty
    private String ordItemNo;
    private String goodsSellTpCd; // 상품판매유형코드(PRD0001)
    private String goodsDlvTpCd;
    @Min(1)
    private long orderCount; // 주문수량
    private long sellAmt;
    private long sellDcAmt;
    private List<Benefit> benefitList;

}
