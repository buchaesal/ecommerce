package com.plateer.ec1.promotion.enums;

public enum PromotionType {
    PRC_DC("PD"), // price discount
    PRD_CUP("PC"), // product coupon
    CART_CUP("CC"); // cart coupon

    String prmType;

    PromotionType(String prmType){
        this.prmType = prmType;
    }
}
