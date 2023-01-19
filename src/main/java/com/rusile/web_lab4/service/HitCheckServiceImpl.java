package com.rusile.web_lab4.service;

import com.rusile.web_lab4.converter.HitCheckRequestToHitEntityConverter;
import com.rusile.web_lab4.converter.HitEntityToHitCheckDTOConverter;
import com.rusile.web_lab4.dao.HitEntity;
import com.rusile.web_lab4.dao.HitRepo;
import com.rusile.web_lab4.dao.UserEntity;
import com.rusile.web_lab4.dao.UserRepo;
import com.rusile.web_lab4.dto.HitCheckRequest;
import com.rusile.web_lab4.dto.HitCheckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HitCheckServiceImpl implements HitCheckService {

    private final HitChecker hitChecker;

    private final HitCheckRequestToHitEntityConverter converter;

    private final HitRepo hitRepo;

    private final UserRepo userRepo;

    private final HitEntityToHitCheckDTOConverter hitEntityToHitCheckDTOConverter;

    @Autowired
    public HitCheckServiceImpl(HitChecker hitChecker, HitCheckRequestToHitEntityConverter converter, HitRepo hitRepo, UserRepo userRepo, HitEntityToHitCheckDTOConverter hitEntityToHitCheckDTOConverter) {
        this.hitChecker = hitChecker;
        this.converter = converter;
        this.hitRepo = hitRepo;
        this.userRepo = userRepo;
        this.hitEntityToHitCheckDTOConverter = hitEntityToHitCheckDTOConverter;
    }


    @Override
    public void checkHit(HitCheckRequest request, Integer userId) {
        long scriptStartTime = Instant.now().getNano();

        HitEntity entity = converter.convert(request);

        Optional<UserEntity> userEntityOptional = userRepo.findById(userId);

        boolean checkHitResult = hitChecker.checkHit(request.coordinates());

        entity.setStatus(checkHitResult);
        entity.setCheckDate(OffsetDateTime.from(Instant.now()));
        entity.setUser(userEntityOptional.get());

        entity.setExecutionTime((Instant.now().getNano() - scriptStartTime) / 1000L);

        hitRepo.save(entity);
    }

    @Override
    public List<HitCheckDTO> getAllHitsByUserId(Integer userId, Double radius) {

        Optional<UserEntity> userEntity = userRepo.findById(userId);
        List<HitEntity> hitEntityList = hitRepo.findAllByUser(userEntity.get());

        List<HitCheckDTO> result = new ArrayList<>();

        hitEntityList.forEach(p -> result.add(hitEntityToHitCheckDTOConverter.convert(p)));

        return result;
    }

    @Override
    public void clearAllByUserId(Integer userId) {
        Optional<UserEntity> userEntity = userRepo.findById(userId);

        hitRepo.deleteAllByUser(userEntity.get());
    }
}
