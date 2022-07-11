package com.plateer.ec1.payment.service;

import com.plateer.ec1.payment.factory.InicisFactory;
import com.plateer.ec1.payment.utils.InicisUtil;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Component
public class InicisApi {

    private final RestTemplate restTemplate;

    public InicisApi(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }


    public Map<String, String> requestVirtualAccount(MultiValueMap<String, String> cancelRequest) {
        String result = Optional.ofNullable(exchangeFormRequest(cancelRequest, InicisFactory.getApiUrl()))
                .orElseThrow(() -> new RuntimeException("api request fail"));
        return InicisUtil.parseJsonToStringMap(result);
    }

    private String exchangeFormRequest(MultiValueMap<String, String> body, String url) {
        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body);
        return restTemplate.exchange(requestEntity, String.class).getBody();
    }

}
