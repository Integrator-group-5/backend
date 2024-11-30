package com.luxury.wear.service.dto.reservation;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    private Boolean saveData;

    @JsonProperty("cedula")
    private String dni;

    @JsonProperty("telefono")
    private String phoneNumber;

    private Long addressId;

    @JsonProperty("pais")
    private String country;

    @JsonProperty("provincia")
    private String province;

    @JsonProperty("ciudad")
    private String city;

    @JsonProperty("direccion")
    private String address;

    @JsonProperty("detalles")
    private String detail;

    @JsonProperty("codigoPostal")
    private String postalCode;
}
