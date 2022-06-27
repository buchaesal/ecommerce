package com.plateer.ec1.common.model.promotion;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CcCpnIssueModel {
    private Long cpnIssNo;
    private String mbrNo;
    private LocalDateTime cpnUseDt;
    private Long orgCpnIssNo;
    private String cpnCertNo;
    private String ordNo;
    private Long prmNo;
    private LocalDateTime sysRegDt;
    private String sysRegId;
    private LocalDateTime sysModDt;
    private String sysModId;
}
