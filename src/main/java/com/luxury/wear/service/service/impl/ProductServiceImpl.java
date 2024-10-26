package com.luxury.wear.service.service.impl;

import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IService<Product> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product delete(Long id) {
        return null;
    }
}
