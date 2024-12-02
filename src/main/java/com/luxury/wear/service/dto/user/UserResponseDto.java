package com.luxury.wear.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxury.wear.service.roles.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    @JsonProperty("cedula")
    private String dni;

    @JsonProperty("telefono")
    private String phoneNumber;

    private UserRole userRole;
}
