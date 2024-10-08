package com.cyberspeed.config;


import com.cyberspeed.domain.model.probabilities.Probabilities;
import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.domain.model.winning.WinningCombination;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * A class representing the game configuration that is typically loaded from a JSON file.
 * This configuration includes details such as the size of the game matrix (rows and columns),
 * the available slot symbols, probability configurations, and the possible winning combinations.
 *
 * <p>This class is designed to be immutable and is used to represent the game state configurations.</p>
 *
 * @param columns         Number of columns in the game matrix.
 * @param rows            Number of rows in the game matrix.
 * @param symbols         A map of symbol names to {@link SlotSymbol} objects representing the available symbols in the game.
 * @param probabilities   A {@link Probabilities} object containing the probabilities for different slot symbols.
 * @param winCombinations A map of winning combination names to {@link WinningCombination} objects, representing the possible
 *                        winning combinations in the game.
 *                        <p>This field is annotated with {@link SerializedName} to match the "win_combinations" field in the JSON.</p>
 */
public record GameConfiguration(int columns, int rows, Map<String, SlotSymbol> symbols, Probabilities probabilities,
                                @SerializedName("win_combinations") Map<String, WinningCombination> winCombinations) {

    /**
     * Constructor for GameConfiguration.
     *
     * @param columns         the number of columns in the game matrix
     * @param rows            the number of rows in the game matrix
     * @param symbols         a map of symbol names to {@link SlotSymbol} objects
     * @param probabilities   a {@link Probabilities} object
     * @param winCombinations a map of winning combination names to {@link WinningCombination} objects
     */
    public GameConfiguration {
    }

    /**
     * Gets the number of columns in the game matrix.
     *
     * @return the number of columns
     */
    @Override
    public int columns() {
        return columns;
    }

    /**
     * Gets the number of rows in the game matrix.
     *
     * @return the number of rows
     */
    @Override
    public int rows() {
        return rows;
    }

    /**
     * Gets the map of slot symbols used in the game.
     * The map keys are the names of the symbols, and the values are {@link SlotSymbol} objects.
     *
     * @return a map of symbol names to {@link SlotSymbol} objects
     */
    @Override
    public Map<String, SlotSymbol> symbols() {
        return symbols;
    }

    /**
     * Gets the {@link Probabilities} object that contains the probabilities for slot symbols.
     *
     * @return the {@link Probabilities} object
     */
    @Override
    public Probabilities probabilities() {
        return probabilities;
    }

    /**
     * Gets the map of winning combinations, which contains the possible ways to win in the game.
     * The map keys are the names of the winning combinations, and the values are {@link WinningCombination} objects.
     *
     * @return a map of winning combination names to {@link WinningCombination} objects
     */
    @Override
    public Map<String, WinningCombination> winCombinations() {
        return winCombinations;
    }
}