package com.luxury.wear.service.controller;

import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product existingProduct = productService.GetProductByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/top-rents")
    public ResponseEntity<List<Product>> getAllTopProducts() {
        List<Product> topProducts = productService.getAllTopProducts();
        return ResponseEntity.status(HttpStatus.OK).body(topProducts);
    }
}
