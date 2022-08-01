package com.plateer.ec1.common.model.order;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OpDvpAreaInfo {

    private String ordNo;
    private Long dvpSeq;
    private String rmtiNm;
    private String rmtiHpNo;
    private String rmtiAddr;
    private String rmtiAddrDtl;
    private LocalDateTime sysRegDtime;
    private String sysRegrId;
    private LocalDateTime sysModDtime;
    private String sysModrId;

}
