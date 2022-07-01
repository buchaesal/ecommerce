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
    private String prmKindCcd;

    public ProductCouponRequest(PromotionRequest reqVO){
        mbrNo = reqVO.getMbrNo();
    }

    public void setProductInfo(Product product){
        productNo = product.getProductNo();
        productPrice = product.getProductAmt();
    }

}
