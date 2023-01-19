package com.rusile.web_lab4.dto;

public record HitCheckDTO(HitCheckRequest request,
                          boolean isHit,
                          long scriptTime) {
}
