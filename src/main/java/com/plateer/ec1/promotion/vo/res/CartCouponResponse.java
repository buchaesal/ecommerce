package com.plateer.ec1.promotion.vo.res;

import com.plateer.ec1.promotion.vo.CouponProduct;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CartCouponResponse extends BaseResponseVO {

    private List<CouponProduct> couponProductList;

}
