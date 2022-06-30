package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.ProductCouponVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ProductCouponResponseVO extends BaseResponseVO {
    private List<ProductCouponVO> productCouponList;
}
