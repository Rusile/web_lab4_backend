package com.rusile.db_module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rusile.db_module.dao.HitRepo;
import com.rusile.db_module.dao.UserEntity;
import com.rusile.db_module.dao.UserRepo;
import com.rusile.db_module.dto.DbCommandDTO;
import com.rusile.db_module.dto.DbResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Optional;

@Component
@Slf4j
public class CommandProcessor {
    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private HitRepo hitRepo;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public void handleClient(Socket clientSocket) {
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

            String requestJson = receiveRequest(in);
            DbCommandDTO request = objectMapper.readValue(requestJson, DbCommandDTO.class);

            DbResponseDTO response = executeCommand(request);

            String responseJson = objectMapper.writeValueAsString(response);
            sendResponse(out, responseJson);

        } catch (IOException e) {
            log.error("Error occurred while handling client", e);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                log.error("Error occurred while closing client socket", e);
            }
        }
    }


    public DbResponseDTO executeCommand(DbCommandDTO request) {
        DbResponseDTO dto = new DbResponseDTO();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            switch (request.getType()) {
                case SAVE_USER -> userRepo.save(request.getUserEntity());
                case SAVE_HIT_ENTITY -> hitRepo.save(request.getHitEntity());
                case FIND_USER_BY_ID -> {
                    Optional<UserEntity> user = userRepo.findById(request.getUserId());
                    dto.setUser(user.isEmpty() ? new UserEntity() : user.get());
                }
                case FIND_USER_BY_LOGIN -> dto.setUser(userRepo.findByLogin(request.getUserLogin()));
                case DELETE_ALL_HIT_ENTITIES_BY_USER -> hitRepo.deleteAllByUser(request.getUserEntity());
                case FIND_ALL_HIT_ENTITIES_BY_USER -> dto.setHitEntityList(hitRepo.findAllByUser(request.getUserEntity()));
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return dto;
    }


    private String receiveRequest(BufferedReader in) throws IOException {
        return in.readLine();
    }

    private void sendResponse(PrintWriter out, String responseJson) {
        out.println(responseJson);
        out.flush();
    }
}
