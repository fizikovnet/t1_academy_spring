package com.fizikovnet.hw.services;

import com.fizikovnet.hw.dao.ProductDAO;
import com.fizikovnet.hw.entity.Product;
import com.fizikovnet.hw.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product findById(long id) {
        return Optional.ofNullable(productDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Optional<List<Product>> findAll() {
        return Optional.of(productDAO.findAll());
    }

    public void save(Product product) {
        productDAO.create(product);
    }

    public int update(Product product) {
        if (product.getId() == null) {
            throw new RuntimeException("User should have id!");
        }
        return productDAO.update(product);
    }

    public void delete(Product product) {
        productDAO.delete(product);
    }

    public List<Product> findAllByUserId(Integer userId) {
        return productDAO.findAllByUserId(userId);
    }
}
