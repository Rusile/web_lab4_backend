package com.rusile.web_lab4.converter;

import com.rusile.web_lab4.dao.HitEntity;
import com.rusile.web_lab4.dto.HitCheckRequest;
import org.springframework.core.convert.converter.Converter;

public class HitCheckRequestToHitEntity implements Converter<HitCheckRequest, HitEntity> {
    @Override
    public HitEntity convert(HitCheckRequest request) {
        HitEntity entity = new HitEntity();

        entity.setX(request.coordinates().getX());
        entity.setY(request.coordinates().getY());
        entity.setR(request.coordinates().getR());

        return  entity;
    }
}
