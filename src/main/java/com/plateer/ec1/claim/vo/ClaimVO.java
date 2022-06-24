package com.plateer.ec1.claim.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClaimVO {
    private String claimCode; //클레임구분코드
    private String productTypeCode; //상품유형코드
    private String odNo; //주문번호
    private String clmNo; //클레임번호
    private List<ClaimItem> clamItemList; //클레임대상상품목록
    private String clamRsnCd; //클레임귀책사유구분코드
}
