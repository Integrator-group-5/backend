package com.luxury.wear.service.controller;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.dto.product.AvailabilityResponse;
import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.service.FileUploadService;
import com.luxury.wear.service.service.product.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "409", description = "Product already exists.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Product with name 'Vestido Azul' already exists."))),
            @ApiResponse(responseCode = "409", description = "Product reference already exists.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Product with reference 'C-451' already exists."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto createdProduct = productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product by id.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Product not found with id: '1'."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id) {
        ProductResponseDto existingProduct = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(existingProduct);
    }

    @GetMapping("/by-reference/{reference}")
    @Operation(summary = "Get a product by reference")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved product by reference.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Product not found with reference: 'C-451'."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<ProductResponseDto> getProductByReference(@PathVariable("reference") String reference) {
        ProductResponseDto existingProduct = productService.getProductByReference(reference);
        return ResponseEntity.status(HttpStatus.OK).body(existingProduct);
    }

    @GetMapping("/by-name/{name}")
    @Operation(summary = "Get a product by name")
    public ResponseEntity<ProductResponseDto> getProductByName(@PathVariable("name") String name) {
        ProductResponseDto existingProduct = productService.getProductByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(existingProduct);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    @ApiResponse(
            responseCode = "200", description = "Successfully retrieved all products.",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))
    )
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
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
    public ResponseEntity<Page<ProductResponseDto>> getAllProductsPaginated(
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
        Page<ProductResponseDto> products;

        if (!category.isEmpty()) {
            products = productService.getProductsByCategory(category, pageable);
        } else if (startDate != null && endDate != null && !search.isEmpty()) {
            products = productService.getAvailableProducts(startDate, endDate, search, pageable);
        } else {
            products = productService.getAllProducts(pageable);
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/top-rents")
    @Operation(summary = "Get all random products")
    @ApiResponse(
            responseCode = "200", description = "Successfully retrieved all random products.",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductResponseDto.class)))
    )
    public ResponseEntity<List<ProductResponseDto>> getAllTopProducts() {
        List<ProductResponseDto> topProducts = productService.getAllTopProducts();
        return ResponseEntity.status(HttpStatus.OK).body(topProducts);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Product not found with id: '1'."))),
            @ApiResponse(responseCode = "404", description = "Category not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Category not found with id: '1'."))),
            @ApiResponse(responseCode = "404", description = "Size not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Size not found with id: '1'."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "An error occurred while processing your request.")))
    })
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto updatedProduct = productService.updateProduct(id, productRequestDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete-product/{id}")
    @Operation(summary = "Delete a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Product not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "Product not found with id: '1'."))),
            @ApiResponse(responseCode = "500", description = "Internal server error.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "string", example = "An error occurred while processing your request.")))
    })
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
