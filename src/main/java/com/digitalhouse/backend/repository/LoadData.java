package com.digitalhouse.backend.repository;

import com.digitalhouse.backend.entity.ImageModel;
import com.digitalhouse.backend.entity.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class LoadData {
    @Autowired
    private ProductRepository productRepository;

    public void loadData() {
        for (int i = 1; i <= 15; i++) {
            ProductModel product = new ProductModel(
                    "Product " + i,
                    "Description " + i,
                    "Material " + i,
                    "Color " + i,
                    "Designer " + i,
                    new BigDecimal(i * 100 + ".00")
            );

            product.setImages(new ArrayList<>(Arrays.asList(
                    new ImageModel("image" + (i * 3 - 2) + ".jpg", product),
                    new ImageModel("image" + (i * 3 - 1) + ".jpg", product),
                    new ImageModel("image" + (i * 3) + ".jpg", product)
            )));

            productRepository.save(product);
        }
    }
}

