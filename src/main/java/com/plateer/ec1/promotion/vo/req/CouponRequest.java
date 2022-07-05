package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.common.model.promotion.CcCpnIssueModel;
import com.plateer.ec1.promotion.validation.Cancel;
import com.plateer.ec1.promotion.validation.Download;
import com.plateer.ec1.promotion.validation.Use;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class CouponRequest {

    @NotEmpty(groups = {Download.class, Use.class, Cancel.class})
    private String mbrNo;
    @NotNull(groups = Download.class)
    private Long prmNo;
    @NotEmpty(groups = Use.class)
    private String ordNo;
    @NotNull(groups = {Use.class, Cancel.class})
    private Long cpnIssNo;


    public AvailableCouponCountRequest makeAvailableCouponCountRequest(){
        return AvailableCouponCountRequest.builder()
                .prmNo(prmNo)
                .mbrNo(mbrNo)
                .build();
    }

    public CcCpnIssueModel makeInsertCouponIssueModel(){
        return CcCpnIssueModel.builder()
                .prmNo(prmNo)
                .mbrNo(mbrNo)
                .build();
    }

    public CcCpnIssueModel makeUpdateUsingCouponModel(){
        return CcCpnIssueModel.builder()
                .cpnIssNo(cpnIssNo)
                .ordNo(ordNo)
                .build();
    }

    public CcCpnIssueModel makeInsertRestoreCouponModel(){
        return CcCpnIssueModel.builder()
                .mbrNo(mbrNo)
                .prmNo(prmNo)
                .cpnIssNo(cpnIssNo)
                .build();
    }

}
