package com.plateer.ec1.claim.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum ClaimType {

    GCC("일반주문취소", ProcessorType.COMPLETE, Arrays.asList("10","20"), Arrays.asList("10"), Boolean.TRUE, "C", ValidatorType.CANCEL),
    MCA("모바일쿠폰주문취소", ProcessorType.ACCEPT_WITHDRAWAL, Arrays.asList("20"), Arrays.asList("20"), Boolean.TRUE, "C", ValidatorType.CANCEL),
    RA("반품접수", ProcessorType.ACCEPT_WITHDRAWAL, Arrays.asList("50"), Arrays.asList("10","20"), Boolean.TRUE, "R", ValidatorType.RETURN_EXCHANGE),
    RW("반품철회", ProcessorType.COMPLETE, Arrays.asList("60"), Arrays.asList("10","20"), Boolean.FALSE, "RC",ValidatorType.RETURN_EXCHANGE),
    XA("교환접수", ProcessorType.ACCEPT_WITHDRAWAL, Arrays.asList("50"), Arrays.asList("10","20"), Boolean.TRUE, "X",ValidatorType.WITHDRAWAL);

//    //모바일쿠폰주문취소완료
//    MCC(ProcessorType.COMPLETE, Arrays.asList()),
//    //반품완료
//    RC(ProcessorType.COMPLETE, Arrays.asList()),
//    //교환철회
//    XW(ProcessorType.ACCEPT, Arrays.asList());

    private final String businessName; //업무명 - 설명을 위함
    private final ProcessorType processorType;
    private final List<String> validStatuses; // 주문진행상태코드
    private final List<String> productTypes; // 상품유형 :모바일/일반
    private final Boolean claimNoFlag; // 클레임번호 채번
    private final String claimCode; //주문클레임구분코드
    private final ValidatorType validatorType;

    public static ClaimType findClaimType(String climTypeCode, String productTypeCode){
        return Arrays.stream(ClaimType.values())
                .filter(t -> t.getClaimCode().equals(climTypeCode) && t.getProductTypes().contains(productTypeCode))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(""));
    }

}
