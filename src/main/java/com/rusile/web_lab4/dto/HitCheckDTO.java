package com.rusile.web_lab4.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.Instant;

@Data
public class HitCheckDTO {

    private static final int R_MAX_VALUE = 3;

    private static final int X_MAX_VALUE = 3;

    private static final int Y_MAX_VALUE = 3;

    private static final int X_MIN_VALUE = -2;

    private static final int Y_MIN_VALUE = -2;

    private Integer id;

    @NotNull
    @Max(X_MAX_VALUE)
    @Min(X_MIN_VALUE)
    private Double x;

    @NotNull
    @Max(Y_MAX_VALUE)
    @Min(Y_MIN_VALUE)
    private Double y;

    @NotNull
    @Max(R_MAX_VALUE)
    @Positive
    private Double r;

    private Instant checkDate;

    private Long executionTime;

    private Boolean status;
}