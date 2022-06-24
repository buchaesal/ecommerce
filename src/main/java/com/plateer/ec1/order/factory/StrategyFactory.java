package com.plateer.ec1.order.factory;

import com.plateer.ec1.order.enums.OrderSystemType;
import com.plateer.ec1.order.enums.OrderType;
import com.plateer.ec1.order.strategy.AfterStrategy;
import com.plateer.ec1.order.strategy.DataStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class StrategyFactory {

    private final Map<OrderType, DataStrategy> dataStrategyMap = new HashMap<>();
    private final Map<OrderSystemType, AfterStrategy> afterStrategyMap = new HashMap<>();

    public StrategyFactory(List<DataStrategy> dataStrategyList,
                           List<AfterStrategy> afterStrategyList){
        dataStrategyList.forEach(d -> dataStrategyMap.put(d.getType(), d));
        afterStrategyList.forEach(a -> afterStrategyMap.put(a.getType(), a));
    }

    public DataStrategy getDataStrategy(OrderType orderType){
        return Optional
                .ofNullable(dataStrategyMap.get(orderType))
                .orElseThrow(() -> new IllegalArgumentException("dataStrategy를 찾을 수 없습니다."));
    }

    public AfterStrategy getAfterStrategy(OrderSystemType systemType){
        return Optional
                .ofNullable(afterStrategyMap.get(systemType))
                .orElseThrow(() -> new IllegalArgumentException("afterStrategy를 찾을 수 없습니다."));
    }


}
