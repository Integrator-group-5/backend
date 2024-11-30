package com.luxury.wear.service.dto.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDto {

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
