package com.plateer.ec1.order.validator;

import com.plateer.ec1.common.code.product.PRD0002;
import com.plateer.ec1.order.vo.OrderValidationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.function.Predicate;

/**
 * 파라미터 유효성검사 - 주문유형별
 */
@Slf4j
public class OrderTypeValidator {

    public static Predicate<OrderValidationVO> isAbleGeneralOrder = (vo) -> {

        // 일반 상품에 일반 배송 상품인지
        vo.getProductViewList().forEach((product) -> {
            if(!PRD0002.GENERAL.code.equals(product.getGoodsDlvTpCd())){
                log.error("delivery code error, product: {}", product);
                throw new IllegalStateException("상품배송유형코드가 잘못되었습니다.");
           }
        });

        // 수취인 주소, 휴대전화번호, 수취인명 유무
        vo.getOrderRequest().getDeliveryList().forEach((product) -> {
            if(!(StringUtils.hasText(product.getRmtiNm())
                    && StringUtils.hasText(product.getRmtiAddr()))){
                log.error("receiver info error, product: {}", product);
                throw new IllegalStateException("수취인 정보 에러");
            }
        });

        return true;
    };

    public static Predicate<OrderValidationVO> isAbleEcouponOrder = (vo) -> {

        // 이쿠폰 상품에 무배송 상품인지
        vo.getProductViewList().forEach((product) -> {
            if(!PRD0002.NO_SHIPPING.code.equals(product.getGoodsDlvTpCd())){
                log.error("delivery code error, product: {}", product);
                throw new IllegalStateException("상품배송유형코드가 잘못되었습니다.");
            }
        });

        // 배송지 수가 1개거나 상품 주문 수량이랑 같은지
        int deliveryAddressCount = vo.getOrderRequest().getDeliveryList().size();
        int productOrderCount = vo.getOrderRequest().getProductList().get(0).getOrderCount();

        if(!(deliveryAddressCount == 1 || deliveryAddressCount == productOrderCount)){
            log.error("delivery Address count error, deliveryAddressCount : {}, productOrderCount : {} ",
                    deliveryAddressCount, productOrderCount);
            throw new IllegalStateException("배송지 수가 잘못되었습니다.");
        }

        return true;

    };

}
