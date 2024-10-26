package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.Image;
import com.luxury.wear.service.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;

@Component
public class LoadData {
    @Autowired
    private ProductRepository productRepository;

    public void loadData() {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setDescription("Description 1");
        product1.setMaterial("Material 1");
        product1.setColor("Color 1");
        product1.setDesigner("Designer 1");
        product1.setPrice(new BigDecimal("100.00"));
        ArrayList<Image> imagesProduct1 = new ArrayList<>();
        imagesProduct1.add(new Image("image1.jpg", product1));
        imagesProduct1.add(new Image("image2.jpg", product1));
        imagesProduct1.add(new Image("image3.jpg", product1));
        product1.setImages(imagesProduct1);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setDescription("Description 2");
        product2.setMaterial("Material 2");
        product2.setColor("Color 2");
        product2.setDesigner("Designer 2");
        product2.setPrice(new BigDecimal("200.00"));
        ArrayList<Image> imagesProduct2 = new ArrayList<>();
        imagesProduct2.add(new Image("image4.jpg", product2));
        imagesProduct2.add(new Image("image5.jpg", product2));
        imagesProduct2.add(new Image("image6.jpg", product2));
        product2.setImages(imagesProduct2);
        productRepository.save(product2);
    }

}

