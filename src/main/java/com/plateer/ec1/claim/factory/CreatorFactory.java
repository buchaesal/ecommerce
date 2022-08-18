package com.plateer.ec1.claim.factory;

import com.plateer.ec1.claim.creator.ClaimCreator;
import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.vo.ClaimVO;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreatorFactory {

    Map<ClaimType, ClaimCreator> creatorMap = new LinkedHashMap<>();

    public CreatorFactory(List<ClaimCreator> creators) {
        creators.forEach(p -> creatorMap.put(p.getType(), p));
    }

    // 상품유형, 클레임유형에따라 달라짐.
    public ClaimCreator findCreator(ClaimVO vo) {

        ClaimType claimType = ClaimType.findClaimType(vo.getClaimCode(), vo.getProductTypeCode());

        if(!creatorMap.containsKey(claimType)) {
            throw new IllegalArgumentException("클레임 creator를 찾을 수 없습니다.");
        }

        return creatorMap.get(claimType);

    }

}
