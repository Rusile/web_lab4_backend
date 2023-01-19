package com.rusile.web_lab4.converter;

import com.rusile.web_lab4.dao.HitEntity;
import com.rusile.web_lab4.dto.HitCheckDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HitCheckRequestToHitEntityConverter implements Converter<HitCheckDTO, HitEntity> {
    @Override
    public HitEntity convert(HitCheckDTO request) {
        HitEntity entity = new HitEntity();

        entity.setX(request.getX());
        entity.setY(request.getY());
        entity.setR(request.getR());

        return  entity;
    }
}
