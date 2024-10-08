package com.cyberspeed.domain.service;

import com.cyberspeed.domain.model.symbols.bonus.BonusSymbolProbabilities;

import java.util.Map;

/**
 * Service for managing bonus symbol probabilities.
 * <p>
 * This service retrieves bonus symbol probabilities from the
 * {@link BonusSymbolProbabilities} object, providing a dedicated interface
 * for accessing bonus probabilities.
 * </p>
 */
public class ProbabilityBonusService {

    private final BonusSymbolProbabilities probabilities;

    /**
     * Constructor to initialize the ProbabilityBonusService with the provided probabilities.
     *
     * @param probabilities the BonusSymbolProbabilities object containing the probabilities
     */

    public ProbabilityBonusService(BonusSymbolProbabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, Integer> getAllProbabilityBonus() {
        return probabilities.symbols();
    }

}
