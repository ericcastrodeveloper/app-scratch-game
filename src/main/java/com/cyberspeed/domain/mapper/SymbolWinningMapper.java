package com.cyberspeed.domain.mapper;

import com.cyberspeed.domain.model.winning.WinningCombination;
import com.cyberspeed.domain.model.winning.WinningCountCombination;
import com.cyberspeed.domain.enums.WhenEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The SymbolWinningMapper class is responsible for mapping symbols to their corresponding winning combinations.
 * This mapping is based on the repetition of symbols in the game matrix and conditions specified by the winning combinations.
 *
 * <p>This class is initialized with a map of {@link WinningCombination} objects, representing the available
 * winning combinations in the game. It provides methods to map repeated symbols to those combinations
 * based on certain conditions.</p>
 */
public class SymbolWinningMapper {

    /**
     * A map of symbol names to {@link WinningCombination} objects, representing the available
     * winning combinations in the game.
     */
    private final Map<String, WinningCombination> winCombinations;

    /**
     * Constructor for SymbolWinningMapper.
     *
     * @param winCombinations a map of symbol names to {@link WinningCombination} objects
     */
    public SymbolWinningMapper(Map<String, WinningCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }

    /**
     * Maps the repeated symbols to their corresponding winning combinations based on the condition.
     *
     * <p>This method iterates through the provided repeated symbols and checks if they meet the criteria
     * for any of the winning combinations. It only considers combinations that match the specified {@link WhenEnum} condition.</p>
     *
     * @param repeatedSymbols a map of symbol names to their count of repetitions
     * @param condition       the {@link WhenEnum} condition that defines when the mapping should occur
     * @return a map of symbol names to a list of corresponding winning combination names
     */
    public Map<String, List<String>> mapSymbolsToWinningCombinations(Map<String, Integer> repeatedSymbols, WhenEnum condition) {
        Map<String, List<String>> repeatedSymbolWinning = new HashMap<>();

        for (Map.Entry<String, WinningCombination> winCombination : winCombinations.entrySet()) {
            if (winCombination.getValue().getWhen().equals(condition)) {
                WinningCountCombination winningCountCombination = (WinningCountCombination) winCombination.getValue();
                mapSymbols(repeatedSymbols, repeatedSymbolWinning, winCombination.getKey(), winningCountCombination);
            }
        }
        return repeatedSymbolWinning;
    }

    /**
     * Maps the symbols to their corresponding winning combination if they match the required repetition count.
     *
     * <p>This method checks if the repeated symbols match the count specified by the {@link WinningCountCombination}.
     * If the counts match, it adds the combination to the map of winning combinations for the corresponding symbol.</p>
     *
     * @param repeatedSymbols         a map of symbol names to their count of repetitions
     * @param repeatedSymbolWinning   a map of symbol names to a list of corresponding winning combination names
     * @param combinationKey          the name of the winning combination
     * @param winningCountCombination the winning combination that specifies the required repetition count
     */
    private void mapSymbols(Map<String, Integer> repeatedSymbols, Map<String, List<String>> repeatedSymbolWinning,
                            String combinationKey, WinningCountCombination winningCountCombination) {
        for (Map.Entry<String, Integer> symbol : repeatedSymbols.entrySet()) {
            if (symbol.getValue().equals(winningCountCombination.getCount())) {
                repeatedSymbolWinning.computeIfAbsent(symbol.getKey(), k -> new ArrayList<>()).add(combinationKey);
            }
        }
    }
}
