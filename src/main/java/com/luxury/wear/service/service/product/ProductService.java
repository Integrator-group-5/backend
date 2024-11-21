package com.luxury.wear.service.service.product;

import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {

    // CRUD operations
    ProductResponseDto createProduct(ProductRequestDto productRequestDto);

    Product GetProductByID(Long id);

    Product getProductByReference(String reference);

    List<Product> getAllProducts();

    Page<Product> getAllProducts(Pageable pageable);

    List<Product> getAllTopProducts();

    Page<Product> getProductsByCategory(String categoryName, Pageable pageable);

    Product updateProduct(Product product);

    void deleteProductById(Long id);

    Page<Product> getAvailableProducts(LocalDate startDate, LocalDate endDate, String search, Pageable pageable);
}
