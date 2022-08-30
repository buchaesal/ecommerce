package com.plateer.ec1.claim.processor;

import com.plateer.ec1.claim.enums.ClaimType;
import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.factory.CreatorFactory;
import com.plateer.ec1.claim.mapper.ClaimMapper;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimItem;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.order.service.OrderHistoryService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ClaimProcessor {

    private final ClaimMapper claimMapper;
    private final CreatorFactory creatorFactory;
    protected final OrderHistoryService orderHistoryService;
    protected final ClaimDataManipulateService manipulateService;

    protected void doValidationProcess(ClaimVO claimVO){
        if(!ClaimType.findClaimTypeByClaimVO(claimVO)
                .getValidStatuses()
                .contains(claimMapper.getOrderInfo(claimVO.getOrdNo()))){
            throw new IllegalArgumentException("Claim Validation Error");
        }
    }

    protected void doClaimDataProcess(ClaimVO claimVO){
        creatorFactory.findCreator(claimVO).execute(claimVO);
    }

    abstract public void doProcess(ClaimVO vo);

    abstract public ProcessorType getType();

}
