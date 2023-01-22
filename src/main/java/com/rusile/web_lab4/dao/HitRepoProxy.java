package com.rusile.web_lab4.dao;

import com.rusile.web_lab4.dao.fastcgi.DBOperationType;
import com.rusile.web_lab4.dao.fastcgi.DbCommandDTO;
import com.rusile.web_lab4.dao.fastcgi.TcpClient;
import com.rusile.web_lab4.exception.DbServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class HitRepoProxy implements HitRepo {

    private final TcpClient tcpClient;

    @Autowired
    public HitRepoProxy(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public List<HitEntity> findAllByUser(UserEntity user) {
        DbCommandDTO request = DbCommandDTO.builder()
                .type(DBOperationType.FIND_ALL_HIT_ENTITIES_BY_USER)
                .userEntity(user)
                .build();

        List<HitEntity> res = new ArrayList<>();
        try {
            res.addAll(tcpClient.sendMessage(request).getHitEntityList());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DbServerException("Server does not work!");
        }

        return res;
    }

    @Override
    public void deleteAllByUser(UserEntity user) {
        DbCommandDTO request = DbCommandDTO.builder()
                .type(DBOperationType.DELETE_ALL_HIT_ENTITIES_BY_USER)
                .userEntity(user)
                .build();

        try {
            tcpClient.sendMessage(request);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DbServerException("Server does not work!");
        }
    }

    @Override
    public void save(HitEntity hitEntity) {
        DbCommandDTO request = DbCommandDTO.builder()
                .type(DBOperationType.SAVE_HIT_ENTITY)
                .hitEntity(hitEntity)
                .build();

        try {
            tcpClient.sendMessage(request);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new DbServerException("Server does not work!");
        }
    }
}
