package com.rusile.web_lab4.controller;

import com.rusile.web_lab4.dto.JwtResponseDTO;
import com.rusile.web_lab4.dto.RefreshTokenDTO;
import com.rusile.web_lab4.dto.UserCredentialsDTO;
import com.rusile.web_lab4.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody @NotNull @Valid UserCredentialsDTO userCredentialsDTO) {
        System.out.println(userCredentialsDTO);
        authService.registerUser(userCredentialsDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    protected ResponseEntity<JwtResponseDTO> loginUser(@RequestBody @NotNull @Valid UserCredentialsDTO userCredentialsDTO) {
        JwtResponseDTO jwtResponseDTO = authService.loginUser(userCredentialsDTO);

        return ResponseEntity.ok().body(jwtResponseDTO);
    }

    @PostMapping("/refresh")
    protected ResponseEntity<JwtResponseDTO> refreshUser(@RequestBody @Valid RefreshTokenDTO refreshTokenDTO) {

        JwtResponseDTO jwtResponseDTO = authService.refreshUser(refreshTokenDTO.getRefreshToken());

        return ResponseEntity.ok().body(jwtResponseDTO);
    }
}
