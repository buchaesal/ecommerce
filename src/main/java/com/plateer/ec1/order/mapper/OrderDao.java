package com.plateer.ec1.order.mapper;

import com.plateer.ec1.order.vo.OrderProduct;
import com.plateer.ec1.order.vo.OrderProductView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDao {

    List<OrderProductView> selectOrderProductViewList(List<OrderProduct> orderProductList);
    String getOrderBenefitNumber();
    boolean validateOrderAmount(String ordNo);

}
