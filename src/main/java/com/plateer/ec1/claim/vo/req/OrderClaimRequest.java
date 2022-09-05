package com.plateer.ec1.claim.vo.req;

import lombok.Builder;

@Builder
public class OrderClaimRequest {

    private String ordNo;
    private String ordGoodsNo;
    private String ordItemNo;
    private String ordClmTpCd;

}
