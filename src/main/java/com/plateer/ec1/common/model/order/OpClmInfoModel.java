package com.plateer.ec1.common.model.order;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class OpClmInfoModel {

    private String ordNo;
    private String ordGoodsNo;
    private String ordItemNo;
    private Long ordSeq;
    private Long procSeq;
    private String ordClmTpCd;
    private String ordPrgsScd;
    private String dvRvtCcd;
    private Long ordAmt;
    private Long ordCnt;
    private Long cnclCnt;
    private Long rtgsCnt;
    private Long dvGrpNo;
    private LocalDateTime ordClmReqDtime;
    private LocalDateTime ordClmAcptDtime;
    private LocalDateTime ordClmCmtDtime;
    private String clmRsnCd;
    private String clmDtlRsnTt;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
    private String clmNo;
    private Long orgProcSeq;

}
