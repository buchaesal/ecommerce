package com.plateer.ec1.common.component;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiComponent {

    private final RestTemplate restTemplate;

    public ApiComponent(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String exchangeFormRequest(MultiValueMap<String, String> body, String url) {
        RequestEntity<MultiValueMap<String, String>> requestEntity = RequestEntity.post(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(body);
        return restTemplate.exchange(requestEntity, String.class).getBody();
    }

}
