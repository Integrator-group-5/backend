package com.luxury.wear.service.service.product;

import com.luxury.wear.service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    // CRUD operations
    Product createProduct(Product product);

    Product GetProductByID(Long id);

    List<Product> getAllProducts();

    Page<Product> getAllProducts(Pageable pageable);

    List<Product> getAllTopProducts();

    Product updateProduct(Product product);

    void deleteProductById(Long id);
}
