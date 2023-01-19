package com.rusile.web_lab4.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findById(Integer id);

    Optional<UserEntity> findByLogin(String login);

    Optional<UserEntity> findByVerificationCode(String oauth);
}
