package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Override
    @EntityGraph(attributePaths = {"category", "sizes"})
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.deleted = false ORDER BY function('RAND')")
    List<Product> findAllTopProductsByDeletedFalse(Pageable pageable);

    Page<Product> findByCategory_NameAndDeletedFalse(String categoryName, Pageable pageable);

    Page<Product> findAllByDeletedFalse(Pageable pageable);

    Optional<Product> findByReference(String reference);

    Optional<Product> findByName(String name);
}
