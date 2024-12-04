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

        String shippingMethod = reservation.isShipping()
                ? "Envió a Domicilio"
                : "Recogida en Tienda";

        String productImageUrl = reservation.getProduct() != null && !reservation.getProduct().getImages().isEmpty()
                ? reservation.getProduct().getImages().get(0).getUrl()
                : null;

        return new ReservationResponseDto(
                reservation.getId(),
                reservation.getReservationCode(),
                reservation.getProduct().getName(),
                productImageUrl,
                reservation.getUser().getEmail(),
                reservation.getDni(),
                reservation.getPhoneNumber(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getTotalCost(),
                shippingMethod,
                reservation.getAddress().getId(),
                reservation.getAddress().getCountry(),
                reservation.getAddress().getProvince(),
                reservation.getAddress().getCity(),
                reservation.getAddress().getAddress(),
                reservation.getAddress().getDetail(),
                reservation.getAddress().getPostalCode()
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
                .shipping(dto.getShipping())
                .build();
    }
}
