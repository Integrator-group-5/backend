package com.luxury.wear.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luxury.wear.service.commons.Constants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = Constants.FIRST_NAME_IS_REQUIRED)
    @Length(min = Constants.NAME_MIN_LENGTH_VALUE, message = Constants.FIRST_NAME_MIN_LENGTH)
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = Constants.LAST_NAME_IS_REQUIRED)
    @Length(min = Constants.NAME_MIN_LENGTH_VALUE, message = Constants.LAST_NAME_MIN_LENGTH)
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = Constants.EMAIL_IS_REQUIRED)
    @Email(message = Constants.EMAIL_IS_NOT_VALID)
    private String email;

    @NotBlank(message = Constants.PASSWORD_IS_REQUIRED)
    @Length(min = Constants.PASSWORD_MIN_LENGTH_VALUE, message = Constants.PASSWORD_MIN_LENGTH)
    private String password;
}
