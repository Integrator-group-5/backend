package com.luxury.wear.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxury.wear.service.roles.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    private String password;

    private UserRole userRole;
}
