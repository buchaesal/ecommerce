package com.plateer.ec1.order.validator;

import com.plateer.ec1.common.code.product.PRD0003;
import com.plateer.ec1.order.exception.OrderValidationException;
import com.plateer.ec1.order.vo.OrderProduct;
import com.plateer.ec1.order.vo.OrderProductView;
import com.plateer.ec1.order.vo.OrderValidationVO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Predicate;

/**
 * 파라메터 유효성검사 - 공통유효성
 */
public class OrderCommonValidator {

    public static Predicate<OrderValidationVO> commonValidator = (vo) -> {

        List<OrderProduct> productList = vo.getOrderRequest().getProductList();
        List<OrderProductView> productViewList = vo.getProductViewList();

        if(CollectionUtils.isEmpty(productViewList)){
            throw new OrderValidationException("상품 정보를 찾을 수 없습니다.");
        }

        productList.forEach((product) -> {
            // 상품(단품)이 존재 하는지 && 판매중 상품인지
            productViewList.stream()
                    .filter(view -> view.getGoodsNo().equals(product.getOrdGoodsNo())
                            && view.getItemNo().equals(product.getOrdItemNo())
                            && PRD0003.ON_SAIL.code.equals(view.getPrgsStatCd()))
                    .findAny()
                    .orElseThrow(() -> new OrderValidationException("상품 정보를 확인해주세요."));
        });

        return true;

    };

}
