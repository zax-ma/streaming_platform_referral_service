package com.stream.platform.service.strategy;

import com.stream.platform.model.ReferralEvent;
import com.stream.platform.service.strategy.enums.ReferralProgramType;

import java.util.List;

public interface ReferralProgramStrategy {
    long calculate(List<ReferralEvent> events);

    default ReferralProgramType getType() {
        return null;
    }
}
