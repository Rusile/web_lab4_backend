package com.rusile.web_lab4.dao;

import java.util.List;

public interface HitRepo {

    List<HitEntity> findAllByUser(UserEntity user);

    void deleteAllByUser(UserEntity user);

    void save(HitEntity hitEntity);
}
