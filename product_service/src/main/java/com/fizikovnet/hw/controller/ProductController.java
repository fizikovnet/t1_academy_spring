package com.fizikovnet.hw.controller;

import com.fizikovnet.hw.entity.Product;
import com.fizikovnet.hw.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/user/{userId}")
    public List<Product> getProductByUserId(@PathVariable("userId") Long userId) {
        return productService.findAllByUserId(userId);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @PutMapping("/update")
    private void updateProduct(@RequestBody Product product) {
        productService.update(product);
    }

}
