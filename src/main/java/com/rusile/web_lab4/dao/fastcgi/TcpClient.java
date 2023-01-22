package com.rusile.web_lab4.dao.fastcgi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
@Slf4j
public class TcpClient {

    @Value("${db_server.host}")
    private String host;

    @Value("${db_server.port}")
    private int port;

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();

    public DbResponseDTO sendMessage(DbCommandDTO request) throws IOException {
        try (Socket socket = new Socket(host, port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            sendRequest(request, out);

            String responseJson = receiveResponse(in, socket);
            return objectMapper.readValue(responseJson, DbResponseDTO.class);
        }
    }

    @Transactional
    protected void sendRequest(DbCommandDTO request, PrintWriter out) throws IOException {
        log.info(request.toString());
        String messageJson = objectMapper.writeValueAsString(request);
        out.println(messageJson);
        out.flush();
    }
    private String receiveResponse(BufferedReader in, Socket socket) throws IOException {

        String response = in.readLine();

        return response;
    }
}
