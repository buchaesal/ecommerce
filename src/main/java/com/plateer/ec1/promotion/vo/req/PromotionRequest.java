package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class PromotionRequest {
    @NotEmpty
    private String mbrNo;
    @NotNull
    private List<@Valid Product> productList;
    @NotNull
    private PromotionType promotionType;
}
