package com.rusile.web_lab4.dto;

import javax.validation.Valid;
import java.time.OffsetDateTime;

public record HitCheckRequest(@Valid Coordinates coordinates, OffsetDateTime time) {

}
