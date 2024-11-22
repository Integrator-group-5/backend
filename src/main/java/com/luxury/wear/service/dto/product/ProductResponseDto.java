package com.luxury.wear.service.dto.product;

import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.entity.Image;
import com.luxury.wear.service.entity.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private Long productId;

    private String name;

    private String reference;

    private String description;

    private String material;

    private String color;

    private String designer;

    private BigDecimal price;

    private List<Image> images = new ArrayList<>();

    private Category category;

    private List<Size> sizes = new ArrayList<>();
}
