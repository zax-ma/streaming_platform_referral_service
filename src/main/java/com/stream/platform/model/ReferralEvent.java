package com.stream.platform.model;

import com.stream.platform.model.enums.SubscriptionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReferralEvent {
    UUID id;
    UUID refereeId;
    @Enumerated(EnumType.STRING)
    SubscriptionType type;
    long bonusDays;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ReferralEvent that = (ReferralEvent) o;
        return bonusDays == that.bonusDays && Objects.equals(id, that.id) && Objects.equals(refereeId, that.refereeId) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, refereeId, type);
    }

    @Override
    public String toString() {
        return "ReferralEvent{" +
                "id=" + id +
                ", refereeId=" + refereeId +
                ", type=" + type +
                ", bonusDays=" + bonusDays +
                '}';
    }
}
