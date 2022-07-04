package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.promotion.enums.PromotionType;
import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class PromotionRequest {
    @NotEmpty
    private String mbrNo;
    @NotEmpty
    private List<Product> productList;
    @NotEmpty
    private PromotionType promotionType;
}
