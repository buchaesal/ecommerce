package com.plateer.ec1.common.model.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class OpOrdBnfInfoModel {

    private String ordBnfNo;
    private Long ordBnfAmt;
    private Long prmNo;
    private Long cpnIssNo;
    private Long ordCnclBnfAmt;
    private String degrCcd;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
    private String cpnKndCd;

}
