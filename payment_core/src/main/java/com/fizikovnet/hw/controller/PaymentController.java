package com.fizikovnet.hw.controller;

import com.fizikovnet.hw.dto.Payment;
import com.fizikovnet.hw.dto.Product;
import com.fizikovnet.hw.dto.ResponseDTO;
import com.fizikovnet.hw.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/products-by-user/{userId}")
    public ResponseDTO<List<Product>> getProductsByUser(@PathVariable("userId") Long userId) {
        return new ResponseDTO<>(HttpStatus.OK.value(), paymentService.getProductsByUserId(userId));
    }

    @PostMapping("/execute")
    public ResponseDTO<String> executePayment(@RequestBody @Valid Payment payment) {
        paymentService.execute(payment);
        return new ResponseDTO<>(HttpStatus.OK.value(), "Successfully updated");
    }

}
