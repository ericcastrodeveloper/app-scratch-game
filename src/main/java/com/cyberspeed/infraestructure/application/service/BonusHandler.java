package com.cyberspeed.infraestructure.application.service;

import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.domain.enums.ImpactEnum;
import com.cyberspeed.domain.model.symbols.bonus.BonusSymbol;

/**
 * The BonusHandler class encapsulates the logic for applying bonus symbols
 * based on the game configuration.
 */
public class BonusHandler {
    private final GameConfiguration gameConfiguration;

    /**
     * Constructs a BonusHandler instance with the specified game configuration.
     *
     * @param gameConfiguration the game configuration containing symbols and bonuses
     */
    public BonusHandler(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
    }

    /**
     * Applies the bonus symbol to the total prize amount.
     *
     * @param totalPrize the current total prize amount
     * @param appliedBonusSymbol the applied bonus symbol
     * @return the updated prize amount after applying the bonus
     */
    public double applyBonus(double totalPrize, String appliedBonusSymbol) {
        if (appliedBonusSymbol != null && !appliedBonusSymbol.isEmpty()) {
            BonusSymbol appliedBonus = (BonusSymbol) gameConfiguration.symbols().get(appliedBonusSymbol);
            if (appliedBonus != null) {
                if (appliedBonus.getImpact().equals(ImpactEnum.EXTRA_BONUS)) {
                    totalPrize += appliedBonus.getExtra();
                }
                if (appliedBonus.getImpact().equals(ImpactEnum.MULTIPLY_REWARD)) {
                    totalPrize *= appliedBonus.getRewardMultiplier();
                }
            }
        }
        return totalPrize;
    }
}