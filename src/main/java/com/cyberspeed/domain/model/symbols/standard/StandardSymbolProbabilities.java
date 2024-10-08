package com.cyberspeed.domain.model.symbols.standard;

import java.util.Collections;
import java.util.Map;

/**
 * Represents the probabilities associated with standard symbols in game.
 * <p>
 * This class contains the row and column information for the symbol probabilities
 * along with a mapping of symbol names to their corresponding probability values.
 * </p>
 *
 * @param row     The row index of the symbol probabilities in the matrix
 * @param column  The column index of the symbol probabilities in the matrix
 * @param symbols A map of symbol names to their associated probabilities
 */
public record StandardSymbolProbabilities(int row, int column, Map<String, Integer> symbols) {
    /**
     * Constructor to initialize the StandardSymbolProbabilities with row, column, and symbols.
     *
     * @param row     the row index of the symbol probabilities
     * @param column  the column index of the symbol probabilities
     * @param symbols the map of symbol names to their associated probabilities
     */
    public StandardSymbolProbabilities(int row, int column, Map<String, Integer> symbols) {
        this.row = row;
        this.column = column;
        this.symbols = Collections.unmodifiableMap(symbols); // To prevent modification
    }

    @Override
    public String toString() {
        return "StandardSymbolProbability{" +
                "column=" + column +
                ", row=" + row +
                ", symbols=" + symbols +
                '}';
    }
}
