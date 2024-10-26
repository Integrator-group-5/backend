package com.digitalhouse.backend.controller;


import com.digitalhouse.backend.entity.ProductModel;
import com.digitalhouse.backend.exception.ResourceNotFoundException;
import com.digitalhouse.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<CustomResponse> getProducts() {
        try {
            List<ProductModel> productos = productService.findAll();
            CustomResponse cr;
            if (productos.isEmpty()) {
                cr = new CustomResponse(true, "No products found", "Empty list");
            } else {
                cr = new CustomResponse(true, "Products found", productos);
            }
            return ResponseEntity.status(200).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error in DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getProductById(@PathVariable Long id) {
        try {
            ProductModel product = productService.findById(id);
            CustomResponse cr = new CustomResponse(true, "Product found", product);
            return ResponseEntity.status(200).body(cr);
        } catch (ResourceNotFoundException e) {
            CustomResponse cr = new CustomResponse(false, "Product not found", e.getMessage());
            return ResponseEntity.status(404).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error in DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }

    @GetMapping("/random")
    public ResponseEntity<CustomResponse> getRandomProducts() {
        try {
            List<ProductModel> productos = productService.findRandomProducts();
            CustomResponse cr;
            if (productos.isEmpty()) {
                cr = new CustomResponse(true, "No products in DB", "Empty list");
            } else {
                cr = new CustomResponse(true, "Random products found", productos);
            }
            return ResponseEntity.status(200).body(cr);
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error in DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }
}
