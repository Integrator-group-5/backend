package com.luxury.wear.service.service.impl;

import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.repository.ProductRepository;
import com.luxury.wear.service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private static final String PRODUCT_NOT_FOUND_ID = "Product not found with Id: ";

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product GetProductByID(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND_ID + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllTopProducts() {
        return productRepository.findAllRandom();
    }

    @Override
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getProductId())
                .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND_ID + product.getProductId()));

        return updateExistingProduct(existingProduct, product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND_ID + id));

        productRepository.deleteById(id);
    }

    private Product updateExistingProduct(Product existingProduct, Product newProductData) {
        existingProduct.setName(newProductData.getName());
        existingProduct.setDescription(newProductData.getDescription());
        existingProduct.setMaterial(newProductData.getMaterial());
        existingProduct.setColor(newProductData.getColor());
        existingProduct.setDesigner(newProductData.getDesigner());
        existingProduct.setPrice(newProductData.getPrice());
        existingProduct.setImages(newProductData.getImages());
        existingProduct.setCategories(newProductData.getCategories());
        existingProduct.setSizes(newProductData.getSizes());

        return productRepository.save(existingProduct);
    }
}
