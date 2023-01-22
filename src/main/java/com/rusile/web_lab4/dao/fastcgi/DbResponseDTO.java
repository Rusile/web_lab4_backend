package com.rusile.web_lab4.dao.fastcgi;

import com.rusile.web_lab4.dao.HitEntity;
import com.rusile.web_lab4.dao.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class DbResponseDTO {
    private UserEntity user;
    private List<HitEntity> hitEntityList;
}
