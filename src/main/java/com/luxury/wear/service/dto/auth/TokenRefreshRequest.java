package com.luxury.wear.service.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest {

    private String refreshToken;
}
