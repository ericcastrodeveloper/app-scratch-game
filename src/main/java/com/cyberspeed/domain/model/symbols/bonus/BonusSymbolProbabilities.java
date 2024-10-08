package com.cyberspeed.domain.model.symbols.bonus;

import java.util.Collections;
import java.util.Map;

/**
 * Represents the probabilities associated with bonus symbols in the game.
 * <p>
 * This class holds a mapping of bonus symbol names to their associated probabilities.
 * </p>
 *
 * @param symbols A map where keys are symbol names and values are their probabilities
 */
public record BonusSymbolProbabilities(Map<String, Integer> symbols) {
    public BonusSymbolProbabilities(Map<String, Integer> symbols) {
        this.symbols = Collections.unmodifiableMap(symbols); // Making the map unmodifiable
    }

    @Override
    public String toString() {
        return "BonusSymbolsProbability{" +
                "symbols=" + symbols +
                '}';
    }
}
