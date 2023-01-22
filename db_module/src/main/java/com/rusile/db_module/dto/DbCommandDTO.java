package com.rusile.db_module.dto;


import com.rusile.db_module.dao.HitEntity;
import com.rusile.db_module.dao.UserEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class DbCommandDTO implements Serializable    {

    private DBOperationType type;

    //nullable args
    private UserEntity userEntity;

    private HitEntity hitEntity;

    private String UserLogin;

    private Integer userId;
}
