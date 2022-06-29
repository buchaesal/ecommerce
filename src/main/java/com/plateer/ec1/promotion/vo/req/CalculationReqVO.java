package com.plateer.ec1.promotion.vo.req;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CalculationReqVO {
    private String productNo;
    private List<Long> couponIssueNoList;
}
