package com.stream.platform.model;

import com.stream.platform.service.strategy.enums.ReferralProgramType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserReferrer {
    UUID userId;
    @Enumerated(EnumType.STRING)
    ReferralProgramType referralProgramType;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserReferrer that = (UserReferrer) o;
        return Objects.equals(userId, that.userId) && Objects.equals(referralProgramType, that.referralProgramType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, referralProgramType);
    }

    @Override
    public String toString() {
        return "UserReferrer{" +
                "userId=" + userId +
                ", referralBonusType='" + referralProgramType + '\'' +
                '}';
    }
}
