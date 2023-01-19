package com.rusile.web_lab4.security.bearer;

import lombok.Data;

@Data
public class CustomBearerUser {

    private final Integer userId;
    private String email;

}