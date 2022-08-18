package com.plateer.ec1.claim.enums;

import com.plateer.ec1.claim.creator.ClaimCreator;
import com.plateer.ec1.claim.vo.ClaimVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ClaimType {

    // 일반주문취소
    GCC(ProcessorType.COMPLETE, Arrays.asList("10","20"), Arrays.asList("10"), Boolean.TRUE, "C"),
    // 모바일쿠폰주문취소요청
    MCA(ProcessorType.ACCEPT_WITHDRAWAL, Arrays.asList("20"), Arrays.asList("20"), Boolean.TRUE, "C"),
    // 반품접수
    RA(ProcessorType.ACCEPT_WITHDRAWAL, Arrays.asList("50"), Arrays.asList("10","20"), Boolean.TRUE, "R"),
    // 반품철회
    RW(ProcessorType.COMPLETE, Arrays.asList("60"), Arrays.asList("10","20"), Boolean.FALSE, "RC"),
    // 교환접수
    XA(ProcessorType.ACCEPT_WITHDRAWAL, Arrays.asList("50"), Arrays.asList("10","20"), Boolean.TRUE, "X");

    private final ProcessorType processorType;
    private final List<String> validStatuses; // 주문진행상태코드
    private final List<String> productTypes; // 상품유형 :모바일/일반
    private final Boolean claimNoFlag; // 클레임번호 채번
    private final String claimCode; //주문클레임구분코드

    public static ClaimType findClaimTypeByClaimVO(ClaimVO claimVO){
        return Arrays.stream(ClaimType.values())
                .filter(t -> t.getClaimCode().equals(claimVO.getClaimCode()) && t.getProductTypes().contains(claimVO.getProductTypeCode()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

    public static ClaimType findClaimType(String climTypeCode, String productTypeCode){
        return Arrays.stream(ClaimType.values())
                .filter(t -> t.getClaimCode().equals(climTypeCode) && t.getProductTypes().contains(productTypeCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

}
