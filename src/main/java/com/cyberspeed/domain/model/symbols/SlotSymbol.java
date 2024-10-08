package com.cyberspeed.domain.model.symbols;

import com.cyberspeed.domain.enums.SymbolTypeEnum;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a symbol in the game.
 * <p>
 * A SlotSymbol can be of different types, such as standard or bonus,
 * defined by the SymbolTypeEnum.
 * </p>
 */
public class SlotSymbol {
    @SerializedName("reward_multiplier")
    private final double rewardMultiplier; // Multiplier applied to the reward when this symbol is part of a winning combination
    private SymbolTypeEnum type; // The type of the slot symbol (e.g., standard, bonus)

    public SlotSymbol(double rewardMultiplier, SymbolTypeEnum type) {
        this.rewardMultiplier = rewardMultiplier;
        this.type = type;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public SymbolTypeEnum getType() {
        return type;
    }

    @Override
    public String toString() {
        return "SlotSymbol{" +
                "type=" + type +
                '}';
    }
}
