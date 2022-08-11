package com.plateer.ec1.claim.processor;

import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.factory.CreatorFactory;
import com.plateer.ec1.claim.factory.ValidatorFactory;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.service.IFCallService;
import com.plateer.ec1.claim.vo.ClaimVO;
import com.plateer.ec1.order.service.OrderHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CompleteProcessor extends ClaimProcessor {

    private final IFCallService ifCallService;

    public CompleteProcessor(ValidatorFactory validatorFactory,
                             CreatorFactory creatorFactory,
                             OrderHistoryService orderHistoryService,
                             ClaimDataManipulateService manipulateService,
                             IFCallService ifCallService) {
        super(validatorFactory, creatorFactory, orderHistoryService, manipulateService);
        this.ifCallService = ifCallService;
    }

    @Transactional
    @Override
    public void doProcess(ClaimVO claimVO) {

        Long logKey = null;
        Exception ex = null;

        try{
            // 모니터링 로그 insert
            logKey = orderHistoryService.insertClaimHistory(claimVO);
            // validation
            doValidationProcess(claimVO);
            // 데이터 insert/update
            doClaimDataProcess(claimVO);
            // 결제 if
            ifCallService.callPaymentIF();
            // 쿠폰 복원 if
            ifCallService.callRestoreCouponIF();
        }catch (Exception e){
            ex = e;
            log.error(e.getMessage());
        }finally {
            orderHistoryService.updateHistory(logKey, claimVO, ex);
        }

    }

    @Override
    public ProcessorType getType() {
        return ProcessorType.COMPLETE;
    }

}
