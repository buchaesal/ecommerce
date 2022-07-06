package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.ProductCoupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductCouponResponseVO extends BaseResponseVO {
    private List<ProductCoupon> productCouponList;
}
