package com.plateer.ec1.payment.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.plateer.ec1.common.model.payment.OpPayInfoModel;
import com.plateer.ec1.payment.enums.PaymentType;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class InicisVirtualAccount extends PaymentResultBase {

    private String resultCode;
    private String tid; // 가상계좌 채번TID
    private String vacct; // 가상계좌번호

    public void validateApprove(){
      if(!PaymentType.INICIS.getApproveSuccessCode().equals(resultCode)){
          throw new RuntimeException("approve rejected");
      }
  }

  public OpPayInfoModel makeInsertModel(OrderInfo orderInfo, PayInfo payInfo){
        return OpPayInfoModel.builder()
                .payNo("C1")
                .ordNo(orderInfo.getOrdNo())
                .payMnCd("")
                .build();
  }

}
