package com.luxury.wear.service.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.entity.Category;
import com.luxury.wear.service.entity.Image;
import com.luxury.wear.service.entity.Size;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = Constants.NAME_IS_REQUIRED)
    @Length(min = Constants.NAME_MIN_LENGTH_VALUE, message = Constants.NAME_MIN_LENGTH)
    @JsonProperty("name")
    private String name;

    @NotBlank(message = Constants.REFERENCE_IS_REQUIRED)
    @Length(min = Constants.REFERENCE_MIN_LENGTH_VALUE, message = Constants.REFERENCE_MIN_LENGTH)
    @JsonProperty("reference")
    private String reference;

    @NotBlank(message = Constants.DESCRIPTION_IS_REQUIRED)
    @Length(min = Constants.DESCRIPTION_MIN_LENGTH_VALUE, message = Constants.DESCRIPTION_MIN_LENGTH)
    @JsonProperty("description")
    private String description;

    @NotBlank(message = Constants.MATERIAL_IS_REQUIRED)
    @Length(min = Constants.MATERIAL_MIN_LENGTH_VALUE, message = Constants.MATERIAL_MIN_LENGTH)
    @JsonProperty("material")
    private String material;

    @NotBlank(message = Constants.COLOR_IS_REQUIRED)
    @Length(min = Constants.COLOR_MIN_LENGTH_VALUE, message = Constants.COLOR_MIN_LENGTH)
    @JsonProperty("color")
    private String color;

    @NotBlank(message = Constants.DESIGNER_IS_REQUIRED)
    @Length(min = Constants.DESIGNER_MIN_LENGTH_VALUE, message = Constants.DESIGNER_MIN_LENGTH)
    @JsonProperty("designer")
    private String designer;

    @NotNull(message = Constants.PRICE_IS_REQUIRED)
    @DecimalMin(value = Constants.PRICE_MIN_VALUE, message = Constants.PRICE_MIN_VALUE_MESSAGE)
    @DecimalMax(value = Constants.PRICE_MAX_VALUE, message = Constants.PRICE_MAX_LENGTH_MESSAGE)
    @JsonProperty("price")
    private BigDecimal price;

    @NotNull(message = Constants.IMAGES_IS_REQUIRED)
    @jakarta.validation.constraints.Size(min = Constants.IMAGES_MIN_LENGTH_VALUE, message = Constants.IMAGES_MIN_LENGTH)
    @jakarta.validation.constraints.Size(max = Constants.IMAGES_MAX_LENGTH_VALUE, message = Constants.IMAGES_MAX_LENGTH)
    @JsonProperty("images")
    private List<Image> images = new ArrayList<>();

    @NotNull(message = Constants.CATEGORY_IS_REQUIRED)
    @JsonProperty("category")
    private Category category;

    @NotNull(message = Constants.SIZE_IS_REQUIRED)
    @jakarta.validation.constraints.Size(min = Constants.SIZE_MIN_LENGTH_VALUE, message = Constants.SIZE_MAX_LENGTH)
    @JsonProperty("sizes")
    private List<Size> sizes = new ArrayList<>();
}
