package com.fizikovnet.repository;

import com.fizikovnet.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByClientId(Integer clientId);
}
