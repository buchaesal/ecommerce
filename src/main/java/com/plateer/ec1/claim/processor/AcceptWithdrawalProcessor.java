package com.plateer.ec1.claim.processor;

import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.factory.CreatorFactory;
import com.plateer.ec1.claim.mapper.ClaimMapper;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.order.service.OrderHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class AcceptWithdrawalProcessor extends ClaimProcessor {

    public AcceptWithdrawalProcessor(ClaimMapper claimMapper,
                                      CreatorFactory creatorFactory,
                                      OrderHistoryService orderHistoryService,
                                      ClaimDataManipulateService manipulateService) {
        super(claimMapper, creatorFactory, orderHistoryService, manipulateService);
    }

    @Transactional
    @Override
    public void doProcess(ClaimVO claimVO) {

        Long logKey = null;
        Exception ex = null;

        try{
            // 클레임 번호 채번
            setClaimNo(claimVO);
            // 모니터링 로그 insert
            logKey = orderHistoryService.insertClaimHistory(claimVO);
            // validation
            doValidationProcess(claimVO);
            // 데이터 insert/update
            doClaimDataProcess(claimVO);

        }catch (Exception e){
            ex = e;
            log.error(e.getMessage());
        }finally {
            orderHistoryService.updateHistory(logKey, claimVO, ex);
        }

    }

    @Override
    public ProcessorType getType() {
        return ProcessorType.ACCEPT_WITHDRAWAL;
    }

    private void setClaimNo(ClaimVO vo){
    }

}
