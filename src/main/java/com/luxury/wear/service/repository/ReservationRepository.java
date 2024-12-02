package com.luxury.wear.service.repository;

import com.luxury.wear.service.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.product.id = :productId AND " +
            "(r.startDate <= :endDate AND r.endDate >= :startDate)")
    List<Reservation> findConflictingReservations(@Param("productId") Long productId,
                                                  @Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate);

    @Query("SELECT DISTINCT r.product.id FROM Reservation r WHERE " +
            "(r.startDate <= :endDate AND r.endDate >= :startDate)")
    List<Long> findUnavailableProductIds(@Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Reservation r WHERE r.product.id = :productId")
    List<Reservation> findByProductId(@Param("productId") Long productId);

    List<Reservation> findByUserEmail(String email);

    Page<Reservation> findByUserEmail(String email, Pageable pageable);
}
