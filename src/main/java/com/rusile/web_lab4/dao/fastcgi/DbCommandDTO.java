package com.rusile.web_lab4.dao.fastcgi;

import com.rusile.web_lab4.dao.HitEntity;
import com.rusile.web_lab4.dao.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DbCommandDTO implements Serializable    {

    private DBOperationType type;

    //nullable args
    private UserEntity userEntity;

    private HitEntity hitEntity;

    private String UserLogin;

    private Integer userId;
}
