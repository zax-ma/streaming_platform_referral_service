package com.stream.platform.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessedReferralEvents {
    List<ReferralEvent> totalEvents;
    long totalBonusDays;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedReferralEvents that = (ProcessedReferralEvents) o;
        return totalBonusDays == that.totalBonusDays && Objects.equals(totalEvents, that.totalEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalEvents, totalBonusDays);
    }

    @Override
    public String toString() {
        return "ProcessedReferralEvents{" +
                "totalEvents=" + totalEvents +
                ", totalBonusDays=" + totalBonusDays +
                '}';
    }
}
