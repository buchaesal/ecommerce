package com.plateer.ec1.payment.vo.api;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class InicisVirtualAccountReqVO {

    @Builder.Default
    private String type = "Pay"; // 고정
    @Builder.Default
    private String paymethod = "Vacct"; // 고정
    private String timestamp; // 전문생성시간 [YYYYMMDDhhmmss]
    private String clientIp; // 가맹점 요청 서버IP (추후 거래 확인 등에 사용됨)
    @Builder.Default
    private String mid = "INIpayTest"; // 상점아이디
    private String url; // 가맹점 URL
    private String moid; // 가맹점주문번호
    private String goodName; // 상품명
    private String buyerName; // 구매자명
    private String buyerEmail; // 구매자 이메일주소("@", "." 외 특수문자 입력불가)
    private String buyerTel; // 구매자 휴대폰번호
    private String price; // 거래금액
    @Builder.Default
    private String currency = "WON"; // 통화코드 [WON,USD]
    private String bankCode; // 은행코드
    private String dtInput; // 입금예정일자 [YYYYMMDD]
    private String tmInput; // 입금예정시간 [hhmm]
    private String nmInput; // 입금자명
//    private String flgCash; // 현금영수증 발행여부 ["0":미발행, "1":소득공제 발행, "2":지출증빙]
//    private String cashRegNo; // 현금영수증 발행정보 (주민번호, 휴대폰번호, 사업장등록번호 등)
//    private String vacctType; // 타입 ["3" 과오납체크] * 과오납 체크의 경우만 세팅
//    private String vacct; // 벌크가상계좌번호 * 과오납 체크의 경우만 세팅
    private String hashData; // 전문위변조 HASH (INIAPIKey+type+paymethod+timestamp+clientIp+mid+moid+price)

}
