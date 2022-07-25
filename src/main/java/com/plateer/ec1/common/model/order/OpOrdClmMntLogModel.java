package com.plateer.ec1.common.model.order;

import com.google.gson.JsonObject;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OpOrdClmMntLogModel {

    private Long logSeq;
    private String ordNo;
    private String clmNo;
    private Object reqPram;
    private Object insData;
    private Object uptData;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
    private String ProcCcd;

}
