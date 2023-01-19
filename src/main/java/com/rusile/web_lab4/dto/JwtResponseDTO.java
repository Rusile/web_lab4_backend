package com.rusile.web_lab4.dto;

import lombok.Data;

@Data
public class JwtResponseDTO {

    private final Integer userId;
    private final String accessToken;
    private final String refreshToken;
}
