package com.fizikovnet.hw.services;

import com.fizikovnet.hw.entity.Product;
import com.fizikovnet.hw.entity.User;
import com.fizikovnet.hw.exceptions.ResourceNotFoundException;
import com.fizikovnet.hw.repository.ProductRepository;
import com.fizikovnet.hw.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Product findById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Optional<List<Product>> findAll() {
        return Optional.of(productRepository.findAll());
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void update(Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("User should have id!");
        }
        productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public List<Product> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId));
        return productRepository.findProductsByUser(user).orElse(List.of());
    }
}
