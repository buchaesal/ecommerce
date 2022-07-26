package com.plateer.ec1.common.model.order;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OpOrdClmMntLogModel {

    private Long logSeq;
    private String ordNo;
    private String clmNo;
    private String reqPram;
    private String insData;
    private String uptData;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
    private String ProcCcd;

}
