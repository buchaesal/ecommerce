package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.ProductCouponVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductCouponResponseVO extends BaseResponseVO {
    private List<ProductCouponVO> productCouponList;
}
