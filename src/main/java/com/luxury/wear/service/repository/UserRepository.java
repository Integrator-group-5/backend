package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Modifying
    @Query(value = "DELETE FROM user_favorites WHERE product_id = :productId", nativeQuery = true)
    void removeProductFromFavorites(@Param("productId") Long productId);
}
