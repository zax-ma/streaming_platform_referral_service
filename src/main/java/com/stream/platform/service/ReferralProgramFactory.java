package com.stream.platform.service;

import com.stream.platform.model.ProcessedReferralEvents;
import com.stream.platform.service.strategy.ReferralProgramStrategy;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ReferralProgramFactory {
    private final Map<ReferralProgramType, ReferralProgramStrategy> strategies;

    public ReferralProgramFactory(List<ReferralProgramStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ReferralProgramStrategy::getType,
                        strategy -> strategy));
    }

    public ReferralProgramStrategy getReferralStrategy(ReferralProgramType type){
        return strategies.getOrDefault(type, events -> new ProcessedReferralEvents(events, 0L));
    }
}
