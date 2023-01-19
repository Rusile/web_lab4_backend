package com.rusile.web_lab4.converter;

import com.rusile.web_lab4.dao.HitEntity;
import com.rusile.web_lab4.dto.Coordinates;
import com.rusile.web_lab4.dto.HitCheckDTO;
import com.rusile.web_lab4.dto.HitCheckRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HitEntityToHitCheckDTOConverter implements Converter<HitEntity, HitCheckDTO> {

    @Override
    public HitCheckDTO convert(HitEntity hitEntity) {
        HitCheckRequest request = new HitCheckRequest(new Coordinates(
                hitEntity.getX(),
                hitEntity.getY(),
                hitEntity.getR()
        ), hitEntity.getCheckDate());

        return new HitCheckDTO(request, hitEntity.getStatus(), hitEntity.getExecutionTime());
    }
}
