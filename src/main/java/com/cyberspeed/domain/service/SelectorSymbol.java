package com.cyberspeed.domain.service;

import com.cyberspeed.domain.exceptions.InvalidRandomValueException;
import com.cyberspeed.utils.RandomGenerator;

import java.util.Map;

/**
 * This class is responsible for selecting a random symbol based on the probabilities
 * aggregated from standard and bonus symbols.
 */
public class SelectorSymbol {

    private final ProbabilityAggregator probabilityAggregator;
    private final RandomGenerator randomGenerator;

    /**
     * Constructor to initialize the SelectorSymbol with the required services.
     *
     * @param probabilityAggregator the service for aggregating probabilities
     * @param randomGenerator the utility for generating random numbers
     */
    public SelectorSymbol( ProbabilityAggregator probabilityAggregator, RandomGenerator randomGenerator) {
        this.probabilityAggregator = probabilityAggregator;
        this.randomGenerator = randomGenerator;
    }

    /**
     * Selects a random symbol key based on the probabilities for a given row and column.
     *
     * @param row the row index for which to select the symbol
     * @param column the column index for which to select the symbol
     * @return the key of the randomly selected symbol
     * @throws RuntimeException if the generated random value is invalid
     */
    public String selectRandomSymbolKey(int row, int column, boolean addedBonus) {
        Map<String, Integer> probabilities = probabilityAggregator.getProbabilities(row, column, addedBonus);
        int totalWeight = ProbabilityCalculator.calculateTotalWeight(probabilities);
        Integer randomValue = randomGenerator.getRandomInteger(totalWeight);

        int currentWeight = 0;
        for (Map.Entry<String, Integer> entry : probabilities.entrySet()) {
            currentWeight += entry.getValue();
            if (randomValue <= currentWeight) {
                return entry.getKey();
            }
        }
        throw new InvalidRandomValueException("Invalid random value!");
    }
}
