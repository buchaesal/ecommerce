package com.plateer.ec1.common.model.order;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OpDvpInfo {

    private Long dvGrpNo;
    private String ordNo;
    private Long dvpSeq;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;
    private String dvMthdCd;

}
