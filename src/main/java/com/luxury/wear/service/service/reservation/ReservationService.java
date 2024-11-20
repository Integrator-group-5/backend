package com.luxury.wear.service.service.reservation;

import com.luxury.wear.service.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    Reservation createReservation(String productName, String userEmail, LocalDate startDate, LocalDate endDate);

    boolean isAvailable(Long productId, LocalDate startDate, LocalDate endDate);

    List<LocalDate> getUnavailableDates(Long productId);
}
