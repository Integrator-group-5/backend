package com.luxury.wear.service.service;


import com.luxury.wear.service.entity.ProductModel;
import com.luxury.wear.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IService<ProductModel> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }

    @Override
    public ProductModel findById(Long id) {
        return null;
    }

    @Override
    public ProductModel save(ProductModel product) {
        return null;
    }

    @Override
    public ProductModel update(ProductModel product) {
        return null;
    }

    @Override
    public ProductModel delete(Long id) {
        return null;
    }
}
