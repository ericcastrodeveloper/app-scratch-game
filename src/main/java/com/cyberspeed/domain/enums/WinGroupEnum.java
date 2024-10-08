package com.cyberspeed.domain.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum representing the different groups of winning combinations in the game.
 * <p>
 * This enum defines various groups that categorize winning combinations based on their arrangement:
 * - SAME_SYMBOLS: Winning combinations formed by the same symbol appearing multiple times.
 * - HORIZONTALLY_LINEAR: Winning combinations that are arranged horizontally.
 * - VERTICALLY_LINEAR: Winning combinations that are arranged vertically.
 * - LTR_DIAGONALLY_LINEAR: Winning combinations arranged diagonally from left to right.
 * - RTL_DIAGONALLY_LINEAR: Winning combinations arranged diagonally from right to left.
 * </p>
 */
public enum WinGroupEnum {
    @SerializedName("same_symbols")
    SAME_SYMBOLS("same_symbols"),

    @SerializedName("horizontally_linear_symbols")
    HORIZONTALLY_LINEAR("horizontally_linear_symbols"),

    @SerializedName("vertically_linear_symbols")
    VERTICALLY_LINEAR("vertically_linear_symbols"),

    @SerializedName("ltr_diagonally_linear_symbols")
    LTR_DIAGONALLY_LINEAR("ltr_diagonally_linear_symbols"),

    @SerializedName("rtl_diagonally_linear_symbols")
    RTL_DIAGONALLY_LINEAR("rtl_diagonally_linear_symbols");

    private final String value;

    WinGroupEnum(String value) {
        this.value = value;
    }

}
