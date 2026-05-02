package com.stream.platform.configuration;

import com.stream.platform.model.enums.SubscriptionType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "referral.fixed")
@Data
public class FixedReferralProgramConfig {
    private Map<SubscriptionType, Long> bonuses;
}
