package com.rusile.web_lab4.service;

import com.rusile.web_lab4.dto.HitCheckRequest;
import com.rusile.web_lab4.dto.HitCheckDTO;
import com.rusile.web_lab4.security.bearer.CustomBearerUser;

import java.util.List;

public interface HitCheckService {

    void checkHit(HitCheckRequest request, Integer userId);

    List<HitCheckDTO> getAllHitsByUserId(Integer userId, Double radius);

    void clearAllByUserId(Integer userId);
}
