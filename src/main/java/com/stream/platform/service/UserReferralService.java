package com.stream.platform.service;

import com.stream.platform.service.strategy.enums.ReferralProgramType;

import java.util.UUID;

public interface UserReferralService {

    ReferralProgramType getReferralTypeByUserId(UUID userId);
}
