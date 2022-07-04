package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.common.code.promotion.PRM0004;
import com.plateer.ec1.common.code.promotion.PRM0012;
import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartCouponRequest {

    private String mbrNo;
    private List<Product> productList;
    private Long prmNo;
    private String cpnKindCd = PRM0004.CART.code;
    private String degrCcd = PRM0012.THIRD.code;

    public CartCouponRequest(PromotionRequest request){
        this.mbrNo = request.getMbrNo();
        this.productList = request.getProductList();
    }

}
