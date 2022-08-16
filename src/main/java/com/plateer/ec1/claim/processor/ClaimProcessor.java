package com.plateer.ec1.claim.processor;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.factory.CreatorFactory;
import com.plateer.ec1.claim.factory.ValidatorFactory;
import com.plateer.ec1.claim.mapper.ClaimMapper;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.order.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ClaimProcessor {

    private final ClaimMapper claimMapper;
    private final ValidatorFactory validatorFactory;
    private final CreatorFactory creatorFactory;
    protected final OrderHistoryService orderHistoryService;
    protected final ClaimDataManipulateService manipulateService;

    protected void doValidationProcess(ClaimVO claimVO){
        validatorFactory.findValidator(ClaimType.findClaimType(claimVO.getClaimCode(), claimVO.getProductTypeCode()).getValidatorType()).validate(claimVO, claimMapper.getOrderInfo(claimVO.getOrdNo()));
    }

    protected void doClaimDataProcess(ClaimVO claimVO){
        creatorFactory.findCreator(claimVO).doCreate(claimVO);
    }

    abstract public void doProcess(ClaimVO vo);

    abstract public ProcessorType getType();

}
