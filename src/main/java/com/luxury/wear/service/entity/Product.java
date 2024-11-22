package com.luxury.wear.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String reference;

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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false, name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_has_size",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "size_id")
    )
    private List<Size> sizes = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();
}
