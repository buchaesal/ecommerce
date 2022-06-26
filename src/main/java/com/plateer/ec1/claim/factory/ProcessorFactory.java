package com.plateer.ec1.claim.factory;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.processor.ClaimProcessor;
import com.plateer.ec1.claim.vo.ClaimVO;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessorFactory {

    Map<ProcessorType, ClaimProcessor> processorMap = new LinkedHashMap<>();

    public ProcessorFactory(List<ClaimProcessor> processors) {
        processors.forEach(p -> processorMap.put(p.getType(), p));
    }

    // 상품유형, 클레임유형에따라 달라짐.
    public ClaimProcessor getProcessor(ClaimVO vo) {
        ProcessorType processorType = ClaimType.findClaimType(vo.getClaimCode(), vo.getProductTypeCode()).getProcessorType();
        if(!processorMap.containsKey(processorType)) {
            throw new IllegalArgumentException("클레임 프로세서를 찾을 수 없습니다.");
        }
        return processorMap.get(processorType);
    }

}
