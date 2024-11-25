package com.luxury.wear.service.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDto {

    private Long id;
    private String productName;
    private String userEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalCost;
}
