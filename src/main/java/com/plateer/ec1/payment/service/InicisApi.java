package com.plateer.ec1.payment.service;

import com.plateer.ec1.common.component.ApiComponent;
import com.plateer.ec1.payment.factory.InicisFactory;
import com.plateer.ec1.payment.utils.InicisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InicisApi {

    private final ApiComponent apiComponent;

    public Map<String, String> requestVirtualAccount(MultiValueMap<String, String> cancelRequest) {
        String result = Optional.ofNullable(apiComponent.exchangeFormRequest(cancelRequest, InicisFactory.getApiUrl()))
                .orElseThrow(() -> new RuntimeException("api request fail"));
        return InicisUtil.parseJsonToStringMap(result);
    }

}
