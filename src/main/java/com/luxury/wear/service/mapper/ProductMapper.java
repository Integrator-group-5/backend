package com.luxury.wear.service.mapper;

import com.luxury.wear.service.dto.product.ProductRequestDto;
import com.luxury.wear.service.dto.product.ProductResponseDto;
import com.luxury.wear.service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponseDto toResponseDto(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getReference(),
                product.getDescription(),
                product.getMaterial(),
                product.getColor(),
                product.getDesigner(),
                product.getPrice(),
                product.getImages(),
                product.getCategory(),
                product.getSizes()
        );
    }

    public Product toEntity(ProductRequestDto productRequestDto) {
        if (productRequestDto == null) {
            return null;
        }

        return Product.builder()
                .name(productRequestDto.getName())
                .reference(productRequestDto.getReference())
                .description(productRequestDto.getDescription())
                .material(productRequestDto.getMaterial())
                .color(productRequestDto.getColor())
                .designer(productRequestDto.getDesigner())
                .price(productRequestDto.getPrice())
                .images(productRequestDto.getImages())
                .category(productRequestDto.getCategory())
                .sizes(productRequestDto.getSizes())
                .build();
    }
}
