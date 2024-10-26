package com.digitalhouse.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "product")
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String material;

    @Column(length = 50)
    private String color;

    @Column(length = 100)
    private String designer;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ImageModel> images;

    // Constructor
    public ProductModel(String name, String description, String material, String color, String designer, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.material = material;
        this.color = color;
        this.designer = designer;
        this.price = price;
    }
}
