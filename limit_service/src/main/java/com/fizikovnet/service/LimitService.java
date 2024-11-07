package com.fizikovnet.service;

import com.fizikovnet.entity.Limit;
import com.fizikovnet.repository.LimitRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@AllArgsConstructor
public class LimitService {

    private LimitRepository limitRepository;

    public Limit getOrCreateClientLimit(Integer clientId) {
        return limitRepository.findByClientId(clientId)
                .orElseGet(() -> {
                    Limit newLimit = new Limit();
                    newLimit.setClientId(clientId);
                    return limitRepository.save(newLimit);
                });
    }

    @Transactional
    public boolean processPayment(Integer clientId, BigDecimal amount) {
        Limit limit = getOrCreateClientLimit(clientId);
        resetDailyLimitIfNeeded(limit);

        if (limit.getRemainingLimit().compareTo(amount) >= 0) {
            limit.setRemainingLimit(limit.getRemainingLimit().subtract(amount));
            limitRepository.save(limit);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void restoreLimit(Integer clientId, BigDecimal amount) {
        Limit limit = getOrCreateClientLimit(clientId);
        resetDailyLimitIfNeeded(limit);

        limit.setRemainingLimit(limit.getRemainingLimit().add(amount));
        if (limit.getRemainingLimit().compareTo(limit.getDailyLimit()) > 0) {
            limit.setRemainingLimit(limit.getDailyLimit());
        }
        limitRepository.save(limit);
    }

    private void resetDailyLimitIfNeeded(Limit limit) {
        if (!limit.getLastReset().isEqual(LocalDate.now())) {
            limit.setRemainingLimit(limit.getDailyLimit());
            limit.setLastReset(LocalDate.now());
            limitRepository.save(limit);
        }
    }

}
