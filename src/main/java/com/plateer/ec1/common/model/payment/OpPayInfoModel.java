package com.plateer.ec1.common.model.payment;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpPayInfoModel {

    private String payNo;
    private String ordNo;
    private String clmNo;
    private String payMnCd; // 결제수단코드(OPT0009)
    private String payCcd; // 결제구분코드(OPT0010)
    private String payPrgsScd; // 결제진행상태코드(OPT0011)
    private Long payAmt;
    private Long cnclAmt;
    private Long rfndAvlAmt; // 환불가능금액
    private String trsnId;
    private LocalDateTime payCmtDtime; // 결제완료일시
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
    private String orgPayNo;
    private String vrAcct; // 계좌번호
    private String vrAcctNm; // 예금주명
    private String vrBnkCd; // 은행코드
    private String vrValDt; // 입금예정일자
    private String vrValTt; // 입금예정시간

}
