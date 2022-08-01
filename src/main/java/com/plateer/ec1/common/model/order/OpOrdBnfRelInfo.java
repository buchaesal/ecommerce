package com.plateer.ec1.common.model.order;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OpOrdBnfRelInfo {

    private String ordNo;
    private Long ordSeq;
    private Long procSeq;
    private String ordBnfNo;
    private String aplyCnclCcd;
    private Long aplyAmt;
    private String clmNo;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;

}
