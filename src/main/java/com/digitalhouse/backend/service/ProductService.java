package com.digitalhouse.backend.service;


import com.digitalhouse.backend.entity.ProductModel;
import com.digitalhouse.backend.exception.EntityAlreadyExistsException;
import com.digitalhouse.backend.exception.ResourceNotFoundException;
import com.digitalhouse.backend.repository.ProductRepository;
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
    public ProductModel findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public ProductModel save(ProductModel productModel) throws ResourceNotFoundException, EntityAlreadyExistsException {
        return null;
    }

    @Override
    public ProductModel update(ProductModel productModel) throws ResourceNotFoundException, EntityAlreadyExistsException {
        return null;
    }

    @Override
    public ProductModel delete(Long id) throws ResourceNotFoundException {
        return null;
    }
}
