package com.plateer.ec1.promotion.vo.req;

import com.plateer.ec1.common.code.promotion.PRM0004;
import com.plateer.ec1.common.code.promotion.PRM0012;
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
    private String cpnKindCd = PRM0004.PRODUCT.code;
    private String degrCcd = PRM0012.FIRST.code;
    private Long cpnIssNo;

    public ProductCouponRequest(PromotionRequest request){
        this.mbrNo = request.getMbrNo();
    }

    public void setProductInfo(Product product){
        this.productNo = product.getProductNo();
        this.productPrice = product.getProductAmt();
        this.cpnIssNo = product.getCpnIssNo();
    }

}
