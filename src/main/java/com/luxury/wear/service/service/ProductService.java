package com.luxury.wear.service.service;

import com.luxury.wear.service.entity.Product;

import java.util.List;

public interface ProductService {

    // CRUD operations
    Product createProduct(Product product);

    Product GetProductByID(Long id);

    List<Product> getAllProducts();

    List<Product> getAllTopProducts();

    Product updateProduct(Product product);

    void deleteProductById(Long id);
}
