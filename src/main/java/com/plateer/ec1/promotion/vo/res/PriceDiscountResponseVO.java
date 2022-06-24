package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PriceDiscountResponseVO extends BaseResponseVO {
    private List<Product> productList;
}
