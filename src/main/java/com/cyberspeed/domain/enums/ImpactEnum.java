package com.cyberspeed.domain.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum representing the types of impacts in the game.
 * <p>
 * This enum defines three types of impacts:
 * - MULTIPLY_REWARD: Represents an impact that multiplies the reward.
 * - EXTRA_BONUS: Represents an impact that provides an extra bonus.
 * - MISS: Represents a situation where no impact occurs (miss).
 * </p>
 */
public enum ImpactEnum {
    @SerializedName("multiply_reward")
    MULTIPLY_REWARD("multiply_reward"),

    @SerializedName("extra_bonus")
    EXTRA_BONUS("extra_bonus"),

    @SerializedName("miss")
    MISS("miss");

    private final String value;

    ImpactEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ImpactEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
