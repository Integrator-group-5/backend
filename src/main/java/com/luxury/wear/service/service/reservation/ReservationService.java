package com.luxury.wear.service.service.reservation;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    ReservationResponseDto createReservation(String userEmail, ReservationRequestDto reservationRequestDto);

    boolean isAvailable(Long productId, LocalDate startDate, LocalDate endDate);

    List<ReservationResponseDto> getUserReservationsById(Long userId);

    Page<ReservationResponseDto> getAllReservationsPaginated(Pageable pageable);

    List<LocalDate> getUnavailableDates(Long productId);
}
