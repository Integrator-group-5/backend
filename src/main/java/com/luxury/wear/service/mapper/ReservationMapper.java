package com.luxury.wear.service.mapper;

import com.luxury.wear.service.dto.reservation.ReservationRequestDto;
import com.luxury.wear.service.dto.reservation.ReservationResponseDto;
import com.luxury.wear.service.entity.Address;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.entity.Reservation;
import com.luxury.wear.service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationResponseDto toResponseDto(Reservation reservation) {
        if (reservation == null) {
            return null;
        }

        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getReservationCode(),
                reservation.getProduct().getName(),
                reservation.getUser().getEmail(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getTotalCost()
        );
    }

    public Reservation toEntity(ReservationRequestDto dto, User user, Product product, Address address) {
        if (dto == null) {
            return null;
        }

        return Reservation.builder()
                .phoneNumber(dto.getPhoneNumber())
                .dni(dto.getDni())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .totalCost(dto.getTotalCost())
                .user(user)
                .product(product)
                .address(address)
                .build();
    }
}
