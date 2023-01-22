package com.rusile.web_lab4.dao;

import java.util.Optional;

public interface UserRepo {

    Optional<UserEntity> findById(Integer id);

    Optional<UserEntity> findByLogin(String login);

    void save(UserEntity user);
}
