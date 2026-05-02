package com.stream.platform.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "referral.progressive")
@Data
public class ProgressiveReferralProgramConfig {
    private long base;
    private List<Double> coefficients;
}
