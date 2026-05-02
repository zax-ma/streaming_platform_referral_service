package com.stream.platform.service.impl;

import com.stream.platform.event.ReferralEventReceivedEvents;
import com.stream.platform.exception.CalculateReferralProgramException;
import com.stream.platform.exception.ProgramTypeNotFoundException;
import com.stream.platform.model.ProcessedReferralEvents;
import com.stream.platform.model.ReferralEvent;
import com.stream.platform.service.ReferralBonusService;
import com.stream.platform.service.ReferralProgramFactory;
import com.stream.platform.service.UserReferralService;
import com.stream.platform.service.strategy.ReferralProgramStrategy;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReferralBonusServiceImpl implements ReferralBonusService {

    private final ReferralProgramFactory referralProgramFactory;
    private final UserReferralService userReferralService;

    @EventListener
    public void onReferralEvents(ReferralEventReceivedEvents event){
        applyBonuses(event.referrerId(), event.events());
    }

    @Override
    public List<ProcessedReferralEvents> applyBonuses(UUID referrerId, List<ReferralEvent> events) {
        if(referrerId == null){
            throw new IllegalArgumentException("referrerId is required");
        }
        if(events == null || events.isEmpty()) return Collections.emptyList();
        ReferralProgramType referrerProgramType = userReferralService.getReferralTypeByUserId(referrerId); // TODO заменить на JPA с запросом by referrerId
        if (referrerProgramType == null){
            throw new ProgramTypeNotFoundException("Referrer program for " + referrerId + " not found");
        }
        ReferralProgramStrategy referralProgram = referralProgramFactory.getReferralStrategy(referrerProgramType);
        try {
            long totalDays = referralProgram.calculate(events);
            return Collections.singletonList(new ProcessedReferralEvents(events, totalDays));
        } catch (RuntimeException e){
            throw new CalculateReferralProgramException("Ошибка в методе расчета бонусных дней: " + e.getMessage());
        }
    }
}
