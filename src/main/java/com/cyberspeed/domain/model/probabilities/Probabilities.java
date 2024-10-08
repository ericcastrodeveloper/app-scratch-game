package com.cyberspeed.domain.model.probabilities;

import com.cyberspeed.domain.model.symbols.bonus.BonusSymbolProbabilities;
import com.cyberspeed.domain.model.symbols.standard.StandardSymbolProbabilities;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents the probabilities associated with standard and bonus symbols in the game.
 * <p>
 * This class contains lists of probabilities for standard symbols and a separate object for bonus symbols.
 * </p>
 *
 * @param standardSymbolProbabilities List of probabilities for standard symbols
 * @param bonusSymbolProbabilities    Probabilities for bonus symbols
 */
public record Probabilities(
        @SerializedName("standard_symbols") List<StandardSymbolProbabilities> standardSymbolProbabilities,
        @SerializedName("bonus_symbols") BonusSymbolProbabilities bonusSymbolProbabilities) {
    /**
     * Constructor to initialize the Probabilities with standard and bonus symbol probabilities.
     *
     * @param standardSymbolProbabilities list of standard symbol probabilities
     * @param bonusSymbolProbabilities    the bonus symbol probabilities
     */
    public Probabilities {
    }

    @Override
    public List<StandardSymbolProbabilities> standardSymbolProbabilities() {
        return standardSymbolProbabilities;
    }

    @Override
    public BonusSymbolProbabilities bonusSymbolProbabilities() {
        return bonusSymbolProbabilities;
    }

    @Override
    public String toString() {
        return "Probabilities{" +
                "standardSymbolProbabilities=" + standardSymbolProbabilities +
                ", bonusSymbolProbabilities=" + bonusSymbolProbabilities +
                '}';
    }
}
