package com.stream.platform.service.impl;

import com.stream.platform.event.ReferralEventReceivedEvents;
import com.stream.platform.model.ReferralEvent;
import com.stream.platform.model.UserReferrer;
import com.stream.platform.service.UserReferralService;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserReferralServiceImpl implements UserReferralService {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void incomingEvents(UUID referrerId, List<ReferralEvent> events) {
        eventPublisher.publishEvent(new ReferralEventReceivedEvents(referrerId, events));
    }

    @Override
    public ReferralProgramType getReferralTypeByUserId(UUID userId) {
        return ReferralProgramType.FIXED; //TODO реализация запроса из БД
    }
}
