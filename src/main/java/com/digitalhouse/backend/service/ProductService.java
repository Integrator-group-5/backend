package com.digitalhouse.backend.service;


import com.digitalhouse.backend.entity.ProductModel;
import com.digitalhouse.backend.exception.EntityAlreadyExistsException;
import com.digitalhouse.backend.exception.ResourceNotFoundException;
import com.digitalhouse.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ResourceNotFoundException("Product not found", "id", id);
        }
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
