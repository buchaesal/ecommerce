package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.promotion.vo.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCouponRequest {

    private String productNo;
    private String mbrNo;
    private Long productPrice;
    private Long prmNo;

    public ProductCouponRequest(PromotionRequest request){
        this.mbrNo = request.getMbrNo();
        this.prmNo = request.getPrmNo();
    }

    public void setProductInfo(Product product){
        this.productNo = product.getProductNo();
        this.productPrice = product.getProductAmt();
    }

}
