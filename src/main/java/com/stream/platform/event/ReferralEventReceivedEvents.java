package com.stream.platform.event;

import com.stream.platform.model.ReferralEvent;

import java.util.List;
import java.util.UUID;

public record ReferralEventReceivedEvents(UUID referrerId, List<ReferralEvent> events) {
}
