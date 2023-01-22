package com.rusile.web_lab4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeartBeatController {
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
