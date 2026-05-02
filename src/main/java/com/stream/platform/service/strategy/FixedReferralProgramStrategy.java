package com.stream.platform.service.strategy;

import com.stream.platform.configuration.FixedReferralProgramConfig;
import com.stream.platform.model.ReferralEvent;
import com.stream.platform.model.enums.SubscriptionType;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FixedReferralProgramStrategy implements ReferralProgramStrategy {
    private final FixedReferralProgramConfig config;

    @Override
    public long calculate(List<ReferralEvent> events) {
        long totalBonusDays = 0L;

        for (ReferralEvent event : events) {
            SubscriptionType type = event.getType();
            long bonusDays = config.getBonuses().getOrDefault(type, 0L);
            event.setBonusDays(bonusDays);
            totalBonusDays += bonusDays;
        }
        return totalBonusDays;
    }

    @Override
    public ReferralProgramType getType() {
        return ReferralProgramType.FIXED;
    }
}
