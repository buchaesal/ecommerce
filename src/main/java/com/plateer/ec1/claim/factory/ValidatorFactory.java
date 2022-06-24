package com.plateer.ec1.claim.factory;

import com.plateer.ec1.claim.enums.ValidatorType;
import com.plateer.ec1.claim.validator.ClaimValidator;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ValidatorFactory {
    private Map<ValidatorType, ClaimValidator> validatorMap = new LinkedHashMap<>();

    public ValidatorFactory(List<ClaimValidator> validators) {
        validators.forEach(v -> validatorMap.put(v.getType(), v));
    }

    public ClaimValidator getValidator(ValidatorType type) {
        if(!validatorMap.containsKey(type)) throw new IllegalArgumentException("클레임 validator를 찾을 수 없습니다.");
        return validatorMap.get(type);
    }
}
