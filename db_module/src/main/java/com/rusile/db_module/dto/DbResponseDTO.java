package com.rusile.db_module.dto;

import com.rusile.db_module.dao.HitEntity;
import com.rusile.db_module.dao.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class DbResponseDTO {
    private UserEntity user;
    private List<HitEntity> hitEntityList;
}
