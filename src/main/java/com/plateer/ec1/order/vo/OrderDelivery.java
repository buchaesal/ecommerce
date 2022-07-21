package com.plateer.ec1.order.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDelivery {

    private Long dvpSeq; // 배송지순번
    private String rmtiNm; // 수취인명
    private String rmtiHpNo; // 수취인전화번호
    private String rmtiAddr; // 수취인 주소
    private String rmtiAddrDtl; // 수취인 상세주소
    private List<OrderGroupDelivery> groupDeliveryList;

}
