package com.luxury.wear.service.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequestDto {

    private String productName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalCost;
}
