package com.plateer.ec1.common.model.order;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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
