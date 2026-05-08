package com.stream.platform.service;

import com.stream.platform.model.ProcessedReferralEvents;
import com.stream.platform.model.ReferralEvent;

import java.util.List;
import java.util.UUID;

public interface ReferralBonusService {
    ProcessedReferralEvents applyBonuses(UUID referrerId, List<ReferralEvent> events);
}
