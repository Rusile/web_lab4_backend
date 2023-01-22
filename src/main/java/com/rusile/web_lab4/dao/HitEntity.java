package com.rusile.web_lab4.dao;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
public class HitEntity {

    private Integer id;

    @NotNull
    private UserEntity user;

    @NotNull
    private Double x;

    @NotNull
    private Double y;

    @NotNull
    private Double r;

    @NotNull
    private OffsetDateTime  checkDate;

    @NotNull
    private Long executionTime;

    @NotNull
    private Boolean status = false;
}