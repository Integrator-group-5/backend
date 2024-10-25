package com.digitalhouse.backend.controller;


import com.digitalhouse.backend.entity.ProductModel;
import com.digitalhouse.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<CustomResponse> getProducts() {
        try {
            List<ProductModel> productos = productService.findAll();
            if (productos.isEmpty()) {
                CustomResponse cr = new CustomResponse(true, "No se encontraron productos", "Empty list");
                return ResponseEntity.status(404).body(cr);
            } else {
                CustomResponse cr = new CustomResponse(true, "Productos encontrados", productos);
                return ResponseEntity.status(200).body(cr);
            }
        } catch (Exception e) {
            CustomResponse cr = new CustomResponse(false, "Error en DB: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(cr);
        }
    }
}
