package com.plateer.ec1.common.model.order;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OpOrdBnfInfo {

    private String ordBnfNo;
    private Long ordBnfAmt;
    private Long prmNo;
    private Long cpnIssNo;
    private Long ordCnclBnfAmt;
    private Long degrCcd;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
    private String cpnKndCd;

}
