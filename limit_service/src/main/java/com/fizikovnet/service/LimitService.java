package com.fizikovnet.service;

import com.fizikovnet.entity.Limit;
import com.fizikovnet.exception.InsufficientLimitException;
import com.fizikovnet.repository.LimitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepository limitRepository;

    public Limit getOrCreateClientLimit(Integer clientId) {
        return limitRepository.findByClientId(clientId)
                .orElseGet(() -> {
                    Limit newLimit = new Limit();
                    newLimit.setClientId(clientId);
                    return limitRepository.save(newLimit);
                });
    }

    @Transactional
    public void processPayment(Integer clientId, BigDecimal amount) {
        Limit limit = getOrCreateClientLimit(clientId);

        if (limit.getRemainingLimit().compareTo(amount) < 0) {
            throw new InsufficientLimitException("Insufficient limit for payment. Available limit: "
                    + limit.getRemainingLimit() + ", Requested: " + amount);
        }
        limit.setRemainingLimit(limit.getRemainingLimit().subtract(amount));
        limitRepository.save(limit);
    }

    @Transactional
    public void restoreLimit(Integer clientId, BigDecimal amount) {
        Limit limit = getOrCreateClientLimit(clientId);

        limit.setRemainingLimit(limit.getRemainingLimit().add(amount));
        if (limit.getRemainingLimit().compareTo(limit.getDailyLimit()) > 0) {
            limit.setRemainingLimit(limit.getDailyLimit());
        }
        limitRepository.save(limit);
    }

}
