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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@Slf4j
public class DbModuleApplication {

    @Value("${tcp.port}")
    private int port;

    @Autowired
    private CommandProcessor commandProcessor;


    public static void main(String[] args) {
        SpringApplication.run(DbModuleApplication.class, args);
    }


    @PostConstruct
    public void startServer() throws IOException {
        Executor executor = Executors.newFixedThreadPool(16);
        ServerSocket serverSocket = new ServerSocket(port);
        log.info("Server started on port " + port);
        while (true) {
            Socket clientSocket = serverSocket.accept();

            executor.execute(() -> commandProcessor.handleClient(clientSocket));

            //commandProcessor.handleClient(clientSocket);

        }
    }
}
