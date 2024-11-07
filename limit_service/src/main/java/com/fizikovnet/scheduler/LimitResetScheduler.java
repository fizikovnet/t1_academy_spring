package com.fizikovnet.scheduler;

import com.fizikovnet.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LimitResetScheduler {

    private final LimitRepository limitRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetDailyLimits() {
        limitRepository.resetAllDailyLimits();
    }
}
