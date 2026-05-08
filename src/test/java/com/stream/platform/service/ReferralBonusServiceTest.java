package com.stream.platform.service;

import com.stream.platform.exception.CalculateReferralProgramException;
import com.stream.platform.exception.ProgramTypeNotFoundException;
import com.stream.platform.model.ProcessedReferralEvents;
import com.stream.platform.model.ReferralEvent;
import com.stream.platform.service.impl.ReferralBonusServiceImpl;
import com.stream.platform.service.impl.UserReferralServiceImpl;
import com.stream.platform.service.strategy.ReferralProgramStrategy;
import com.stream.platform.service.strategy.enums.ReferralProgramType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReferralBonusServiceTest {
    @Mock
    private UserReferralServiceImpl userReferralService;

    @Mock
    private ReferralProgramFactory referralProgramFactory;

    @Mock
    private ReferralProgramStrategy referralProgramStrategy;

    @InjectMocks
    private ReferralBonusServiceImpl referralBonusService;

    private UUID referrerId;
    private List<ReferralEvent> events;

    @BeforeEach
    void setUp() {
        referrerId = UUID.randomUUID();
        events = List.of(new ReferralEvent());
    }

    @Test
    void shouldApplyBonusesSuccessfully() {

        ReferralProgramType type = ReferralProgramType.FIXED;
        when(userReferralService.getReferralTypeByUserId(referrerId)).thenReturn(type);
        when(referralProgramFactory.getReferralStrategy(type)).thenReturn(referralProgramStrategy);
        when(referralProgramStrategy.calculate(events)).thenReturn(new ProcessedReferralEvents(Collections.emptyList(), 44L));

        ProcessedReferralEvents result = referralBonusService.applyBonuses(referrerId, events);

        assertNotNull(result);
        assertEquals(44L, result.getTotalBonusDays());
        verify(referralProgramStrategy).calculate(events);
    }

    @Test
    void shouldThrowExceptionWhenReferrerIdIsNull() {
        assertThrows(IllegalArgumentException.class, () ->
                referralBonusService.applyBonuses(null, events)
        );
    }

    @Test
    void shouldReturnEmptyListWhenEventsAreEmpty() {
        ProcessedReferralEvents result = referralBonusService.applyBonuses(referrerId, Collections.emptyList());
        assertNotNull(result);
    }

    @Test
    void shouldThrowExceptionWhenProgramTypeNotFound() {
        when(userReferralService.getReferralTypeByUserId(referrerId)).thenReturn(null);

        assertThrows(ProgramTypeNotFoundException.class, () ->
                referralBonusService.applyBonuses(referrerId, events)
            );
    }

    @Test
    void shouldThrowCalculateExceptionOnStrategyError() {
        ReferralProgramType type = ReferralProgramType.FIXED;
        when(userReferralService.getReferralTypeByUserId(referrerId)).thenReturn(type);
        when(referralProgramFactory.getReferralStrategy(type)).thenReturn(referralProgramStrategy);
        when(referralProgramStrategy.calculate(events)).thenThrow(new RuntimeException("Data error"));

        CalculateReferralProgramException exception = assertThrows(
                CalculateReferralProgramException.class,
                () -> referralBonusService.applyBonuses(referrerId, events)
        );
        assertEquals("Ошибка в методе расчета бонусных дней: " + "Data error", exception.getMessage());
    }
}
