package com.cyberspeed.domain.service;

import java.util.Map;

/**
 * Utility class for calculating probabilities in the game.
 * <p>
 * This class provides static methods to perform calculations related to
 * probabilities, such as calculating the total weight of a given set of probabilities.
 * </p>
 */
public class ProbabilityCalculator {

    /**
     * Calculates the total weight of the given probabilities.
     *
     * @param probabilities a map of probabilities where keys are symbol names and values are their respective weights
     * @return the total weight of the probabilities
     */
    public static int calculateTotalWeight(Map<String, Integer> probabilities) {
        return probabilities.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
