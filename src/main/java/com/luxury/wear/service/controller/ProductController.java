package com.luxury.wear.service.controller;

import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.luxury.wear.service.service.reservation.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@Tag(name = "Product", description = "Endpoints for managing products")
public class ProductController {

    private final ProductService productService;
    private final ReservationService reservationService;

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product existingProduct = productService.GetProductByID(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingProduct);
    }

    @GetMapping("/by-reference/{reference}")
    @Operation(summary = "Get a product by reference")
    public ResponseEntity<Product> getProductByReference(@PathVariable("reference") String reference) {
        Product existingProduct = productService.getProductByReference(reference);
        return ResponseEntity.status(HttpStatus.OK).body(existingProduct);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/paginated")
    @Operation(summary = "Get all products paginated with default page size of 6")
    public ResponseEntity<Page<Product>> getAllProductsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "") String category,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(defaultValue = "") String search) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getAllProducts(pageable);

        if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category, pageable);
        }

        if (startDate != null && endDate != null && (search != null && !search.isEmpty())) {
            products = productService.getAvailableProducts(startDate, endDate, search, pageable);
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/top-rents")
    @Operation(summary = "Get all random products")
    public ResponseEntity<List<Product>> getAllTopProducts() {
        List<Product> topProducts = productService.getAllTopProducts();
        return ResponseEntity.status(HttpStatus.OK).body(topProducts);
    }

    @DeleteMapping("/delete-product/{id}")
    @Operation(summary = "Delete a product by id")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Map<String, List<LocalDate>>> getProductAvailability(@PathVariable Long id) {

        List<LocalDate> unavailableDates = reservationService.getUnavailableDates(id);
        Map<String, List<LocalDate>> response = new HashMap<>();
        response.put("unavailableDates", unavailableDates);

        return ResponseEntity.ok(response);
    }
}
