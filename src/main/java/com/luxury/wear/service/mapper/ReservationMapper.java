package com.luxury.wear.service.mapper;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import com.luxury.wear.service.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationResponseDto toResponseDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getProduct().getName(),
                reservation.getUser().getEmail(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getTotalCost()
        );
    }

    public Reservation toEntity(ReservationRequestDto reservationRequestDto) {
        if (reservationRequestDto == null) {
            return null;
        }

        return Reservation.builder()
                .startDate(reservationRequestDto.getStartDate())
                .endDate(reservationRequestDto.getEndDate())
                .totalCost(reservationRequestDto.getTotalCost())
                .build();
    }
}
