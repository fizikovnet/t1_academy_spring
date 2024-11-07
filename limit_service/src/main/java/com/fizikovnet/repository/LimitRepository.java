package com.fizikovnet.repository;

import com.fizikovnet.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByClientId(Integer clientId);

    @Modifying
    @Transactional
    @Query("UPDATE Limit l SET l.remainingLimit = l.dailyLimit, l.lastReset = CURRENT_DATE")
    void resetAllDailyLimits();
}
