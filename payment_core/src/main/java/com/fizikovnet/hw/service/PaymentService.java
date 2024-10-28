package com.fizikovnet.hw.service;

import com.fizikovnet.hw.dto.Payment;
import com.fizikovnet.hw.dto.Product;
import com.fizikovnet.hw.dto.ResponseDTO;
import com.fizikovnet.hw.exception.NotEnoughBalanceException;
import com.fizikovnet.hw.exception.ProductNotFoundException;
import com.fizikovnet.hw.exception.UserNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.text.MessageFormat;
import java.util.List;

import static java.text.MessageFormat.format;

@Service
public class PaymentService {

    private final RestClient restClient;

    public PaymentService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Product> getProductsByUserId(Long userId) {
        return restClient.get()
                .uri("/user/" + userId)
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    throw new UserNotFoundException(format("User with id {0} not found", userId));
                })
                .body(new ParameterizedTypeReference<>() {});
    }

    public void execute(Payment payment) {
        Product product = restClient.get()
                .uri("/" + payment.getProductId())
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    throw new ProductNotFoundException(
                            "Product with id " + payment.getProductId() + " not found. Payment was rejected"
                    );
                })
                .body(new ParameterizedTypeReference<>() {});

        if (payment.getWriteOff() && (product.getBalance() < payment.getSumma())) {
            throw new NotEnoughBalanceException("Not enough money on balance for this operation");
        }

        if (payment.getWriteOff()) {
            product.setBalance(product.getBalance() - payment.getSumma());
        } else {
            product.setBalance(product.getBalance() + payment.getSumma());
        }

        restClient.put()
                .uri("/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Integer.class);
    }
}
