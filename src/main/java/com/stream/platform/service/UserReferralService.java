package com.stream.platform.service;

import com.stream.platform.model.ReferralEvent;
import com.stream.platform.service.strategy.enums.ReferralProgramType;

import java.util.List;
import java.util.UUID;

public interface UserReferralService {
    void incomingEvents(UUID referrerId, List<ReferralEvent> events);
    ReferralProgramType getReferralTypeByUserId(UUID userId);
}
