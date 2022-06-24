package com.plateer.ec1.claim.processor;

import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.factory.ValidatorFactory;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.service.IFCallService;
import com.plateer.ec1.claim.service.MonitoringLogService;
import com.plateer.ec1.claim.vo.ClaimVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompleteProcessor extends ClaimProcessor {

    private final IFCallService ifCallService;

    public CompleteProcessor(ValidatorFactory validatorFactory,
                             MonitoringLogService monitoringLogService,
                             ClaimDataManipulateService manipulateService,
                             IFCallService ifCallService) {
        super(validatorFactory, monitoringLogService, manipulateService);
        this.ifCallService = ifCallService;
    }

    @Override
    public void doProcess(ClaimVO claimVO) {
        log.info("클레임 Complete Processor start");
        Long logKey = null;

        try{
            // 모니터링 로그 insert
            logKey = monitoringLogService.insertMonitoringLog("log");
            // validation
            doValidationProcess(claimVO);
            // 데이터 insert/update
            doClaimDataProcess(claimVO);
            // 결제 if
            ifCallService.callPaymentIF();
            // 쿠폰 복원 if
            ifCallService.callRestoreCouponIF();
        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            monitoringLogService.updateMonitortingLog(logKey, "");
        }

    }

    @Override
    public ProcessorType getType() {
        return ProcessorType.COMPLETE;
    }

}
