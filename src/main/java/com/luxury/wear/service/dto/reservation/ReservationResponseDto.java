package com.luxury.wear.service.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDto {

    private Long id;

    private String reservationCode;

    private String productName;

    private String userEmail;

    private String dni;

    private String phoneNumber;

    private LocalDate startDate;

    private LocalDate endDate;

    private BigDecimal totalCost;

    private String shippingMethod;

    private Long addressId;

    private String country;

    private String province;

    private String city;

    private String address;

    private String detail;

    private String postalCode;
}
