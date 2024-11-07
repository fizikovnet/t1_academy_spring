package com.fizikovnet.scheduler;

import com.fizikovnet.repository.LimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LimitResetScheduler {

    @Autowired
    private LimitRepository limitRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void resetDailyLimits() {
        limitRepository.findAll().forEach(limit -> {
            limit.setRemainingLimit(limit.getDailyLimit());
            limit.setLastReset(LocalDate.now());
            limitRepository.save(limit);
        });
    }
}
