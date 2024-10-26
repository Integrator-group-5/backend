package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.ImageModel;
import com.luxury.wear.service.entity.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class LoadData {
    @Autowired
    private ProductRepository productRepository;

    public void loadData() {
        ProductModel product1 = new ProductModel();
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setMaterial("Material 1");
        product1.setColor("Color 1");
        product1.setDesigner("Designer 1");
        product1.setPrice(new BigDecimal("100.00"));
        ArrayList<ImageModel> imagesProduct1 = new ArrayList<>();
        imagesProduct1.add(new ImageModel("image1.jpg", product1));
        imagesProduct1.add(new ImageModel("image2.jpg", product1));
        imagesProduct1.add(new ImageModel("image3.jpg", product1));
        product1.setImages(imagesProduct1);
        productRepository.save(product1);

        ProductModel product2 = new ProductModel();
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setMaterial("Material 2");
        product2.setColor("Color 2");
        product2.setDesigner("Designer 2");
        product2.setPrice(new BigDecimal("200.00"));
        ArrayList<ImageModel> imagesProduct2 = new ArrayList<>();
        imagesProduct2.add(new ImageModel("image4.jpg", product2));
        imagesProduct2.add(new ImageModel("image5.jpg", product2));
        imagesProduct2.add(new ImageModel("image6.jpg", product2));
        product2.setImages(imagesProduct2);
        productRepository.save(product2);
    }

}

