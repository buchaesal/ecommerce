package com.plateer.ec1.claim.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MonitoringLogService {

    public Long insertMonitoringLog(String moniteringLog) {
        log.info("클레임 insert 모니터링 로그 - log : {}", moniteringLog);
        return null;
    }

    public void updateMonitortingLog(Long id, String moniteringLog) {
        log.info("클레임 update 모니터링 로그 - log : {}", moniteringLog);
    }

}
