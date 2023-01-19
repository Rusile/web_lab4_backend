package com.rusile.web_lab4.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserCredentialsDTO {

    @Size(min = 6, max = 20)
    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6, max = 25)
    private String password;
}
