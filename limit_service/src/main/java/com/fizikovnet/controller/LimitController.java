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

    private LimitService limitService;

    public LimitController(LimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Limit> getLimit(@PathVariable Integer clientId) {
        Limit limit = limitService.getOrCreateClientLimit(clientId);
        return ResponseEntity.ok(limit);
    }

    @PostMapping("/{clientId}/payment")
    public ResponseEntity<String> processPayment(@PathVariable Integer clientId, @RequestParam BigDecimal amount) {
        boolean success = limitService.processPayment(clientId, amount);
        if (success) {
            return ResponseEntity.ok("Payment processed");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient limit");
        }
    }

    @PostMapping("/{clientId}/restore")
    public ResponseEntity<String> restoreLimit(@PathVariable Integer clientId, @RequestParam BigDecimal amount) {
        limitService.restoreLimit(clientId, amount);
        return ResponseEntity.ok("Limit restored");
    }
}
