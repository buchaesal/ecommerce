package com.plateer.ec1.claim.processor;

import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.factory.ValidatorFactory;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.service.MonitoringLogService;
import com.plateer.ec1.claim.vo.ClaimVO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ClaimProcessor {

    private final ValidatorFactory validatorFactory;
    protected final MonitoringLogService monitoringLogService;
    protected final ClaimDataManipulateService manipulateService;

    protected void doValidationProcess(ClaimVO claimVO){
//        claimValidator.validate(claimVO);
    }

    protected void doClaimDataProcess(ClaimVO claimVO){
//        ClaimDataCreator claimDataCreator = ClaimType.findCreator(claimDto.getClaimType());
//        manipulator.insertClaimData(claimDataCreator.getInsertClaimData(claimDto));
//        manipulator.updateClaimData(claimDataCreator.getUpdateClaimData(claimDto));
    }

    abstract public void doProcess(ClaimVO vo);

    abstract public ProcessorType getType();

}
