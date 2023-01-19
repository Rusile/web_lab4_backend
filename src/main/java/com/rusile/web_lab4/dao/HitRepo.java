package com.rusile.web_lab4.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HitRepo extends CrudRepository<HitEntity, Integer> {

    List<HitEntity> findAllByUser(UserEntity user);

    void deleteAllByUser(UserEntity user);
}
