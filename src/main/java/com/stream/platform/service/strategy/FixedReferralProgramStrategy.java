package com.stream.platform.service.strategy;

import com.stream.platform.configuration.FixedReferralProgramConfig;
import com.stream.platform.model.ProcessedReferralEvents;
import com.stream.platform.model.ReferralEvent;
import com.stream.platform.model.enums.SubscriptionType;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FixedReferralProgramStrategy implements ReferralProgramStrategy {
    private final FixedReferralProgramConfig config;

    @Override
    public ProcessedReferralEvents calculate(List<ReferralEvent> events) {
        long totalBonusDays = 0L;
        List<ReferralEvent> resultEvent = new ArrayList<>();
        for (ReferralEvent event : events) {
            SubscriptionType type = event.getType();
            long bonusDays = config.getBonuses().getOrDefault(type, 0L);
            ReferralEvent copy = event.toBuilder()
                    .bonusDays(bonusDays)
                    .build();
            resultEvent.add(copy);
            totalBonusDays += bonusDays;
        }
        return new ProcessedReferralEvents(resultEvent, totalBonusDays);
    }

    @Override
    public ReferralProgramType getType() {
        return ReferralProgramType.FIXED;
    }
}
