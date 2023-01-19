package com.rusile.web_lab4.dto;

public record HitCheckResponse(HitCheckRequest request,
                               boolean isHit,
                               long scriptTime) {
}
