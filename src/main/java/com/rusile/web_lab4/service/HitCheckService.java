package com.rusile.web_lab4.service;

import com.rusile.web_lab4.dto.HitCheckDTO;

import java.util.List;

public interface HitCheckService {

    void checkHit(HitCheckDTO request, Integer userId);

    List<HitCheckDTO> getAllHitsByUserId(Integer userId, Double radius);

    void clearAllByUserId(Integer userId);
}
