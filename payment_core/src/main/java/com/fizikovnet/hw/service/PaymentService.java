package com.fizikovnet.hw.service;

import com.fizikovnet.hw.dto.Payment;
import com.fizikovnet.hw.dto.Product;
import com.fizikovnet.hw.dto.ResponseDTO;
import com.fizikovnet.hw.exception.NotEnoughBalanceException;
import com.fizikovnet.hw.exception.ProductNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PaymentService {

    private final RestClient restClient;

    public PaymentService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Product> getProductsByUserId(Integer userId) {
        return restClient.get()
                .uri("/user/" + userId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public int execute(Payment payment) {
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

        Integer result = restClient.put()
                .uri("http://localhost:8080/api/products/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(product)
                .retrieve()
                .body(Integer.class);

        return result;
    }
}
