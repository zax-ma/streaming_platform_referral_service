package com.stream.platform.service.impl;

import com.stream.platform.service.UserReferralService;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserReferralServiceImpl implements UserReferralService {

    @Override
    public ReferralProgramType getReferralTypeByUserId(UUID userId) {
        return ReferralProgramType.FIXED; //TODO реализация запроса из БД
    }
}
