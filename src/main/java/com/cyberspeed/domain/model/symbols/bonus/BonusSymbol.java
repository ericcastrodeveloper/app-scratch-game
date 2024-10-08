package com.cyberspeed.domain.model.symbols.bonus;


import com.cyberspeed.domain.enums.ImpactEnum;
import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.domain.enums.SymbolTypeEnum;

/**
 * Represents a bonus symbol in the game.
 * <p>
 * A bonus symbol can have a specific impact on the game,
 * providing additional benefits or multipliers to the player.
 * </p>
 */
public class BonusSymbol extends SlotSymbol {
    private final ImpactEnum impact; // Describes the impact of the bonus symbol (e.g., "multiply_reward", "extra_bonus", "miss")
    private final Integer extra; // Represents any extra value associated with the bonus

    /**
     * Constructor to initialize the BonusSymbol with specified impact and extra values.
     *
     * @param impact the impact of the bonus symbol
     * @param extra  any extra value associated with the bonus
     */
    public BonusSymbol(double rewardMultiplier, SymbolTypeEnum type, ImpactEnum impact, Integer extra) {
        super(rewardMultiplier, type);
        this.impact = impact;
        this.extra = extra;
    }

    public ImpactEnum getImpact() {
        return impact;
    }

    public Integer getExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return "BonusSymbol{" +
                "impact='" + impact + '\'' +
                ", extra=" + extra +
                ", type=" + getType() +
                '}';
    }
}
