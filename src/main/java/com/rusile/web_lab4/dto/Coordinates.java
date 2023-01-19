package com.rusile.web_lab4.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class Coordinates {

    private static final int R_MAX_VALUE = 3;

    private static final int X_MAX_VALUE = 3;

    private static final int Y_MAX_VALUE = 3;

    private static final int X_MIN_VALUE = -2;

    private static final int Y_MIN_VALUE = -2;


    @NotBlank
    @Max(X_MAX_VALUE)
    @Min(X_MIN_VALUE)
    private final Double x;

    @NotBlank
    @Max(Y_MAX_VALUE)
    @Min(Y_MIN_VALUE)
    private final Double y;

    @NotBlank
    @Max(R_MAX_VALUE)
    @Positive
    private final Double r;

}
