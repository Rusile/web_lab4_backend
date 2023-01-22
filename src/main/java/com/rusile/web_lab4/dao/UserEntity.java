package com.rusile.web_lab4.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserEntity {

    private Integer id;

    private String login;

    @Size(max = 255)
    @NotNull
    private String password;

    @Size(max = 64)
    private String verificationCode;
}