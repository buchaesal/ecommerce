package com.plateer.ec1.order.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderProduct {

    @NotEmpty
    private String ordGoodsNo;
    @NotEmpty
    private String ordItemNo;
    private String goodsSellTpCd; // 상품판매유형코드(PRD0001)
    @NotEmpty
    @Min(1)
    private Integer orderCount; // 주문수량
    private List<Benefit> benefitList;

}
