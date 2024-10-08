package com.cyberspeed.domain.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum representing the types of symbols in the game.
 * <p>
 * This enum defines two types of symbols:
 * - STANDARD: Represents regular symbols used in the game.
 * - BONUS: Represents special symbols.
 * </p>
 */
public enum SymbolTypeEnum {
    @SerializedName("standard")
    STANDARD("standard"),
    @SerializedName("bonus")
    BONUS("bonus");

    private final String value;

    SymbolTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SymbolTypeEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
