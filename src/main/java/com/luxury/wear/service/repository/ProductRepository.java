package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY function('RAND')")
    List<Product> findAllRandom(Pageable pageable);

    Page<Product> findByCategories_Name(String categoryName, Pageable pageable);

    Optional<Product> findByReference(String reference);
}
