package com.cyberspeed.domain.service;

import com.cyberspeed.domain.model.symbols.standard.StandardSymbolProbabilities;
import com.cyberspeed.shared.exceptions.ConfigurationException;

import java.util.List;
import java.util.Map;

/**
 * Service class for managing probabilities of standard symbols in the game.
 * <p>
 * This class provides methods to retrieve probabilities based on the specified
 * row and column in the configuration.
 * </p>
 */
public class ProbabilityStandardService {

    private final List<StandardSymbolProbabilities> probabilities;

    /**
     * Constructor to initialize the ProbabilityStandardService with a list of probabilities.
     *
     * @param probabilities a list of StandardSymbolProbabilities
     */
    public ProbabilityStandardService(List<StandardSymbolProbabilities> probabilities) {
        this.probabilities = probabilities;
    }

    /**
     * Retrieves the probabilities for a given row and column.
     *
     * @param row    the row index for the probability
     * @param column the column index for the probability
     * @return a map of symbol names to their respective weights for the specified row and column
     * @throws RuntimeException if multiple entries are found for the same row and column for standard symbol
     */
    public Map<String, Integer> getProbabilitiesByRowAndColumn(int row, int column) {
        List<StandardSymbolProbabilities> symbols = probabilities.stream().
                filter(probability -> probability.row() == row && probability.column() == column)
                .toList();

        if(symbols.size() > 1) {
            throw new ConfigurationException("Found repeated row and column in config for standard symbols");
        } else
            // If no symbols were found for the specified row and column, return the symbols from the first entry
            if(symbols.isEmpty()) {
                return probabilities.getFirst().symbols();
            }

        return symbols.getFirst().symbols();
    }
}
