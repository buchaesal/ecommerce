package com.plateer.ec1.claim.service;

import com.plateer.ec1.claim.factory.ProcessorFactory;
import com.plateer.ec1.claim.processor.ClaimProcessor;
import com.plateer.ec1.claim.vo.ClaimVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClaimService {

    private final ProcessorFactory processorFactory;

    public void claim(ClaimVO vo){
        ClaimProcessor claimProcessor = processorFactory.getProcessor(vo);
        claimProcessor.doProcess(vo);
    }

}
