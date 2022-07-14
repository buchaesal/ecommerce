package com.plateer.ec1.payment.vo.req;

import com.plateer.ec1.common.code.order.OPT0011;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ChangeDepositCompleteRequest extends OpPayInfoModel {

    private String noReqTid;

    public ChangeDepositCompleteRequest(Map<String, String> apiResult){

        noReqTid = apiResult.get("no_req_tid");
        super.setTrsnId(apiResult.get("no_tid"));
        super.setRfndAvlAmt(Long.valueOf(apiResult.get("amt_input")));
        super.setPayPrgsScd(OPT0011.COMPLETE_APPROVE.code);

    }

}
