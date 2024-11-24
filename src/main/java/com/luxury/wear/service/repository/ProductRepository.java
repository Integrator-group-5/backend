package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p ORDER BY function('RAND')")
    List<Product> findAllRandom(Pageable pageable);

    Page<Product> findByCategory_Name(String categoryName, Pageable pageable);

    Optional<Product> findByReference(String reference);

    Optional<Product> findByName(String name);

    @Query("""
        SELECT p FROM Product p
        WHERE p.id NOT IN :unavailableProductIds
          AND (LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(p.reference) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(p.material) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(p.color) LIKE LOWER(CONCAT('%', :search, '%'))
               OR LOWER(p.designer) LIKE LOWER(CONCAT('%', :search, '%')))
        """)
    Page<Product> findAvailableProductsWithSearch(
            @Param("unavailableProductIds") List<Long> unavailableProductIds,
            @Param("search") String search,
            Pageable pageable);
}
