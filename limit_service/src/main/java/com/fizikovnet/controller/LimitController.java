package com.fizikovnet.controller;

import com.fizikovnet.entity.Limit;
import com.fizikovnet.service.LimitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/limits")
public class LimitController {

    private final LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/{clientId}")
    public Limit getLimit(@PathVariable Integer clientId) {
        return limitService.getOrCreateClientLimit(clientId);
    }

    @PostMapping("/{clientId}/payment")
    public String processPayment(@PathVariable Integer clientId, @RequestParam BigDecimal amount) {
        limitService.processPayment(clientId, amount);

        return "Payment processed";
    }

    @PostMapping("/{clientId}/restore")
    public String restoreLimit(@PathVariable Integer clientId, @RequestParam BigDecimal amount) {
        limitService.restoreLimit(clientId, amount);

        return "Limit restored";
    }
}
