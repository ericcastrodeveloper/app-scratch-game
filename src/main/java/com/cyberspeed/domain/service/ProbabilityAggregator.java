package com.cyberspeed.domain.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Aggregates probabilities from standard and bonus probability services.
 * This class is responsible for merging the probabilities from both sources.
 */
public class ProbabilityAggregator {
    private final ProbabilityStandardService standardService;
    private final ProbabilityBonusService bonusService;

    /**
     * Constructs a ProbabilityAggregator with the given services.
     *
     * @param standardService the service for standard probabilities
     * @param bonusService    the service for bonus probabilities
     */
    public ProbabilityAggregator(ProbabilityStandardService standardService, ProbabilityBonusService bonusService) {
        this.standardService = standardService;
        this.bonusService = bonusService;
    }

    /**
     * Combines standard and bonus probabilities for a specific row and column.
     *
     * @param row    the row index
     * @param column the column index
     * @return a map containing combined probabilities for standard and bonus symbols
     */
    public Map<String, Integer> getProbabilities(int row, int column, boolean withBonus) {
        Map<String, Integer> probabilitiesStandard = standardService.getProbabilitiesByRowAndColumn(row, column);
        Map<String, Integer> probabilitiesBonus = bonusService.getAllProbabilityBonus();

        if (withBonus) {
            Map<String, Integer> unionMap = new HashMap<>(probabilitiesStandard);
            unionMap.putAll(probabilitiesBonus);
            return unionMap;
        } else {
            return probabilitiesStandard;
        }
    }
}
