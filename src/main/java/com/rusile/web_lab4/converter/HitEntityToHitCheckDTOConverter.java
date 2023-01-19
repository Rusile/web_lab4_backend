package com.rusile.web_lab4.converter;

import com.rusile.web_lab4.dao.HitEntity;
import com.rusile.web_lab4.dto.Coordinates;
import com.rusile.web_lab4.dto.HitCheckDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class HitEntityToHitCheckDTOConverter implements Converter<HitEntity, HitCheckDTO> {

    @Override
    public HitCheckDTO convert(HitEntity hitEntity) {
        HitCheckDTO dto = new HitCheckDTO();

        dto.setX(hitEntity.getX());
        dto.setY(hitEntity.getY());
        dto.setR(hitEntity.getR());
        dto.setId(hitEntity.getId());
        dto.setStatus(hitEntity.getStatus());
        dto.setExecutionTime(hitEntity.getExecutionTime());
        dto.setCheckDate(Instant.from(hitEntity.getCheckDate()));

        return dto;
    }
}
