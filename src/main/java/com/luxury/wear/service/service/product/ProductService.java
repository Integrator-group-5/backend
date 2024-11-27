package com.luxury.wear.service.service.product;

import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ProductService {

    // CRUD operations
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    ProductResponseDto getProductById(Long id);

    ProductResponseDto getProductByReference(String reference);

    ProductResponseDto getProductByName(String name);

    List<ProductResponseDto> getAllProducts();

    Page<ProductResponseDto> getAllProducts(Pageable pageable);

    List<ProductResponseDto> getAllTopProducts();

    Page<ProductResponseDto> getProductsByCategory(String categoryName, Pageable pageable);

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto);

    void deleteProductById(Long id);

    Page<ProductResponseDto> getAvailableProducts(LocalDate startDate, LocalDate endDate, String search, Pageable pageable);

    Set<String> extractKeywords();
}
