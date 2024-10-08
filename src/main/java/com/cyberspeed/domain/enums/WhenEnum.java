package com.cyberspeed.domain.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum representing the conditions for winning combinations in the game.
 * <p>
 * This enum defines two conditions:
 * - SAME_SYMBOLS: Represents winning combinations that occur when the same symbol appears multiple times.
 * - LINEAR_SYMBOLS: Represents winning combinations that occur when symbols are arranged linearly.
 * </p>
 */
public enum WhenEnum {
    @SerializedName("same_symbols")
    SAME_SYMBOLS("same_symbols"),
    @SerializedName("linear_symbols")
    LINEAR_SYMBOLS("linear_symbols");

    private final String value;

    WhenEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "WhenEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
