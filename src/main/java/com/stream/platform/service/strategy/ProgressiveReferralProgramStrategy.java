package com.stream.platform.service.strategy;

import com.stream.platform.configuration.ProgressiveReferralProgramConfig;
import com.stream.platform.model.ReferralEvent;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProgressiveReferralProgramStrategy implements ReferralProgramStrategy {

    private final ProgressiveReferralProgramConfig config;

    @Override
    public long calculate(List<ReferralEvent> events) {
        long totalBonusDays = 0L;
        long baseDays = config.getBase();
        List<Double> coefficients = config.getCoefficients();

        for (int i = 0; i < events.size(); i++) {
            double factor = (i < coefficients.size())
                    ? coefficients.get(i)
                    : coefficients.getLast();
            long bonusDays = (long) (baseDays * factor);

            events.get(i).setBonusDays(bonusDays);
            totalBonusDays += bonusDays;
        }
        return totalBonusDays;
    }

    @Override
    public ReferralProgramType getType() {
        return ReferralProgramType.PROGRESSIVE;
    }
}
