package com.rusile.web_lab4.controller;

import com.rusile.web_lab4.dto.HitCheckDTO;
import com.rusile.web_lab4.dto.HitCheckRequest;
import com.rusile.web_lab4.security.bearer.CustomBearerUser;
import com.rusile.web_lab4.service.HitCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("api/v1")
@Controller
public class HitCheckController {

    private final HitCheckService hitCheckService;

    @Autowired
    public HitCheckController(HitCheckService hitCheckService) {
        this.hitCheckService = hitCheckService;
    }


    @DeleteMapping("/hits")
    public ResponseEntity<?> clearHits(@AuthenticationPrincipal CustomBearerUser customBearerUser) {
        hitCheckService.clearAllByUserId(customBearerUser.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hits")
    public List<HitCheckDTO> getAllHits(@AuthenticationPrincipal CustomBearerUser customBearerUser,
                                        @RequestParam(required = false) Double radius) {

        List<HitCheckDTO> result = hitCheckService.getAllHitsByUserId(customBearerUser.getUserId(), radius);
        return result;
    }

    @PostMapping("/hits")
    public ResponseEntity<?> checkHit(@AuthenticationPrincipal CustomBearerUser customBearerUser,
                                      @Valid @NotNull HitCheckRequest request) {

        hitCheckService.checkHit(request, customBearerUser.getUserId());
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
