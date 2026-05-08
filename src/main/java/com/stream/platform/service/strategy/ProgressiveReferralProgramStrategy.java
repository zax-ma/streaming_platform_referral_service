package com.stream.platform.service.strategy;

import com.stream.platform.configuration.ProgressiveReferralProgramConfig;
import com.stream.platform.model.ProcessedReferralEvents;
import com.stream.platform.model.ReferralEvent;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgressiveReferralProgramStrategy implements ReferralProgramStrategy {

    private final ProgressiveReferralProgramConfig config;

    @Override
    public ProcessedReferralEvents calculate(List<ReferralEvent> events) {
        long totalBonusDays = 0L;
        long baseDays = config.getBase();
        List<ReferralEvent> resultEvents = new ArrayList<>();
        List<Double> coefficients = config.getCoefficients();

        for (int i = 0; i < events.size(); i++) {
            double factor = (i < coefficients.size())
                    ? coefficients.get(i)
                    : coefficients.getLast();
            long bonusDays = (long) (baseDays * factor);
            ReferralEvent copy = events.get(i).toBuilder()
                    .bonusDays(bonusDays)
                    .build();
            resultEvents.add(copy);
            totalBonusDays += bonusDays;
        }
        return new ProcessedReferralEvents(resultEvents, totalBonusDays);
    }

    @Override
    public ReferralProgramType getType() {
        return ReferralProgramType.PROGRESSIVE;
    }
}
