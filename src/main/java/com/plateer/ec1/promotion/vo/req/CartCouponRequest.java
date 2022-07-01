package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.common.code.promotion.PRM0004;
import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartCouponRequest {

    private String prmKindCcd;
    private String mbrNo;
    private List<Product> productList;
    private Long prmNo;

    public CartCouponRequest(PromotionRequest request){
        this.prmKindCcd = PRM0004.CART.code;
        this.mbrNo = request.getMbrNo();
        this.productList = request.getProductList();
    }

}
