package com.rusile.web_lab4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/hits")
@Controller
public class HitsController {

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearHits(@RequestHeader(name = "Authorization") String oauth) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllHits(@RequestHeader(name = "Authorization") String oauth) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/check")
    public ResponseEntity<?> checkHit(@RequestHeader(name = "Authorization") String oauth) {

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
