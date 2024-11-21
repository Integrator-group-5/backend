package com.luxury.wear.service.controller;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.dto.product.AvailabilityResponse;
import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.service.FileUploadService;
import com.luxury.wear.service.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.luxury.wear.service.service.reservation.ReservationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@Slf4j
@Tag(name = "Product", description = "Endpoints for managing products")
public class ProductController {

    private final ProductService productService;
    private final ReservationService reservationService;
    private final FileUploadService fileUploadService;

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto createdProduct = productService.createProduct(productRequestDto);
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
    @Operation(
            summary = "Get all products paginated with filtering options",
            description = "Retrieves a paginated list of products with optional filters for category, availability dates, and search query."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved paginated products.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Invalid parameters provided."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<Page<Product>> getAllProductsPaginated(
            @Parameter(description = "Page number to retrieve (0-based index)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of items per page", example = "6")
            @RequestParam(defaultValue = "6") int size,

            @Parameter(description = "Category filter for products (optional)", example = "Novias")
            @RequestParam(defaultValue = "") String category,

            @Parameter(description = "Start date for availability filter (optional, format: yyyy-MM-dd)", example = "2023-12-01")
            @RequestParam(required = false) LocalDate startDate,

            @Parameter(description = "End date for availability filter (optional, format: yyyy-MM-dd)", example = "2023-12-10")
            @RequestParam(required = false) LocalDate endDate,

            @Parameter(description = "Search query to filter products by name or description (optional)", example = "azul")
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
    @Operation(
            summary = "Get Product Availability",
            description = "Returns a list of unavailable dates for a specific product based on its reservations."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved unavailable dates.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AvailabilityResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Product not found."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string",
                                    example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<AvailabilityResponse> getProductAvailability(
            @Parameter(description = "ID of the product to retrieve availability for", required = true, example = "1")
            @PathVariable Long id) {

        List<LocalDate> unavailableDates = reservationService.getUnavailableDates(id);
        AvailabilityResponse response = new AvailabilityResponse(unavailableDates);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setProductId(id);
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload an image")
    public ResponseEntity<Map<String, String>> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("category") String category) {

        Map<String, String> response = new HashMap<>();
        try {
            String filePath = fileUploadService.uploadFile(file, name, Constants.PRODUCT_UPLOAD_DIR + "/" + category);
            response.put("response", filePath.replace("public/", "/")); // Example for generating a relative path
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            response.put("response", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

        } catch (IOException e) {
            String errorMessage = "Error saving file.";
            response.put("response", errorMessage);
            log.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
