package com.rusile.web_lab4.dao;

import com.rusile.web_lab4.dao.fastcgi.DBOperationType;
import com.rusile.web_lab4.dao.fastcgi.DbCommandDTO;
import com.rusile.web_lab4.dao.fastcgi.TcpClient;
import com.rusile.web_lab4.exception.DbServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@Slf4j
public class UserRepoProxy implements UserRepo {

    private final TcpClient tcpClient;

    public UserRepoProxy(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        DbCommandDTO request = DbCommandDTO.builder()
                .type(DBOperationType.FIND_USER_BY_ID)
                .userId(id)
                .build();

        Optional<UserEntity> result = Optional.empty();
        try {
            UserEntity entity = tcpClient.sendMessage(request)
                    .getUser();
            Optional<UserEntity> entityOptional = entity.getId() == null ? Optional.empty() : Optional.of(entity);

            result = entityOptional;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DbServerException("Server does not work!");
        }

        return result;
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        DbCommandDTO request = DbCommandDTO.builder()
                .type(DBOperationType.FIND_USER_BY_LOGIN)
                .UserLogin(login)
                .build();

        Optional<UserEntity> result = Optional.empty();
        try {
            Optional<UserEntity> entity = Optional.ofNullable(tcpClient.sendMessage(request)
                    .getUser());

            result = entity;
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DbServerException("Server does not work!");
        }

        return result;
    }

    @Override
    public void save(UserEntity user) {
        DbCommandDTO request = DbCommandDTO.builder()
                .type(DBOperationType.FIND_USER_BY_ID)
                .userEntity(user)
                .build();

        try {
            tcpClient.sendMessage(request);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DbServerException("Server does not work!");
        }
    }
}
