package com.plateer.ec1.common.model.promotion;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
