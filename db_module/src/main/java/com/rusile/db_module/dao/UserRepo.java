package com.rusile.db_module.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);
}
