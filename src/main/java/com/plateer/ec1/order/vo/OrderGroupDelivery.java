package com.plateer.ec1.order.vo;

import com.plateer.ec1.promotion.vo.Product;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Builder
public class OrderGroupDelivery {

    private Long dvGrpNo;
    private List<Product> productList;
    private @NotEmpty List<@Valid DeliveryFee> feeList;

}
