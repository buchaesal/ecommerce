package com.plateer.ec1.claim.processor;

import com.plateer.ec1.claim.enums.ProcessorType;
import com.plateer.ec1.claim.factory.CreatorFactory;
import com.plateer.ec1.claim.factory.ValidatorFactory;
import com.plateer.ec1.claim.service.ClaimDataManipulateService;
import com.plateer.ec1.claim.service.MonitoringLogService;
import com.plateer.ec1.claim.vo.ClaimVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AcceptWithdrawalProcessor extends ClaimProcessor {

    private AcceptWithdrawalProcessor(ValidatorFactory validatorFactory,
                                      CreatorFactory creatorFactory,
                                      MonitoringLogService monitoringLogService,
                                      ClaimDataManipulateService manipulateService) {
        super(validatorFactory, creatorFactory, monitoringLogService, manipulateService);
    }

    @Override
    public void doProcess(ClaimVO claimVO) {

        log.info("클레임 Accept Processor start");
        Long logKey = null;

        try{
            // 클레임 번호 채번
            setClaimNo(claimVO);
            // 모니터링 로그 insert
            logKey = monitoringLogService.insertMonitoringLog("");
            // validation
            doValidationProcess(claimVO);
            // 데이터 insert/update
            doClaimDataProcess(claimVO);

        }catch (Exception e){
            log.error(e.getMessage());
        }finally {
            monitoringLogService.updateMonitortingLog(logKey, "");
        }

    }

    @Override
    public ProcessorType getType() {
        return ProcessorType.ACCEPT_WITHDRAWAL;
    }

    private void setClaimNo(ClaimVO vo){
    }

}
