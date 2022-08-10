package com.plateer.ec1.order.validator;

import com.plateer.ec1.common.code.product.PRD0003;
import com.plateer.ec1.order.vo.OrderProduct;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderValidationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Predicate;

/**
 * 파라메터 유효성검사 - 공통유효성
 */
@Slf4j
public class OrderCommonValidator {

    public static Predicate<OrderValidationVO> commonValidator = (vo) -> {

        List<OrderProduct> productList = vo.getOrderRequest().getProductList();
        List<OrderProductView> productViewList = vo.getProductViewList();

        if(CollectionUtils.isEmpty(productViewList)){
            throw new IllegalArgumentException("상품 정보를 찾을 수 없습니다.");
        }

        productList.forEach((product) -> {
            productViewList.forEach((productView) -> {

                // 1. 상품(단품)이 존재 하는지
                if(!(productView.getGoodsNo().equals(product.getOrdGoodsNo())
                        && productView.getItemNo().equals(product.getOrdItemNo()))){
                    log.error("not found product : {}, view: {}", product, productView);
                    throw new RuntimeException("상품 정보가 존재하지 않습니다.");
                }

                // 2. 상품 판매 상태 확인
                if(!PRD0003.ON_SAIL.code.equals(productView.getPrgsStatCd())){
                    log.error("product progress status error : {}, view: {}", product, productView);
                    throw new RuntimeException("상품진행상태코드 확인");
                }

            });
        });

        return true;

    };

}
