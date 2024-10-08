package com.cyberspeed.infraestructure.application.service;

import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.domain.enums.SymbolTypeEnum;
import com.cyberspeed.domain.enums.WhenEnum;
import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.domain.model.symbols.standard.StandardSymbol;
import com.cyberspeed.domain.model.winning.WinningCombination;
import com.cyberspeed.utils.SymbolUtils;

import java.util.List;
import java.util.Map;

/**
 * The GameReward class encapsulates the logic for calculating the prize based on
 * the game configuration and applied winning combinations.
 * <p>
 * This class calculates the total prize based on the bet amount, the winning combinations
 * applied to the symbols, and any bonus symbols present. It ensures that the correct reward
 * multipliers are used for standard symbols and handles the application of bonus symbols.
 * </p>
 */
public class GameReward {

    private final GameConfiguration gameConfiguration;
    private final SymbolUtils symbolUtils;
    private final BonusHandler bonusHandler;

    /**
     * Constructs a GameReward instance with the specified game configuration,
     * symbol utility, and bonus handler.
     *
     * @param gameConfiguration the game configuration containing symbols and winning combinations
     * @param symbolUtils      utility class for symbol-related operations
     */
    public GameReward(GameConfiguration gameConfiguration, SymbolUtils symbolUtils) {
        this.gameConfiguration = gameConfiguration;
        this.symbolUtils = symbolUtils;
        this.bonusHandler = new BonusHandler(gameConfiguration); // Initialize BonusHandler
    }

    /**
     * Calculates the total prize based on the bet amount, applied winning combinations,
     * and any bonus symbol. It iterates over each applied winning combination, calculates
     * the prize for each symbol, and applies any relevant bonus.
     *
     * @param betAmount                  the amount the user has bet
     * @param appliedWinningCombinations the winning combinations applied to the symbols
     * @param appliedBonusSymbol         the applied bonus symbol, if any
     * @return the calculated total prize amount after applying bonuses
     */
    public double calculatePrize(double betAmount, Map<String, List<String>> appliedWinningCombinations,
                                 String appliedBonusSymbol) {
        if (appliedWinningCombinations.isEmpty()) {
            return 0;
        }

        double totalPrize = 0;

        // Calculate the prize for each symbol based on its winning combinations
        for (Map.Entry<String, List<String>> entry : appliedWinningCombinations.entrySet()) {
            String symbol = entry.getKey();
            List<String> combinations = entry.getValue();
            totalPrize += calculateSymbolPrize(betAmount, symbol, combinations);
        }

        // Apply bonus symbol effect, if any
        totalPrize = bonusHandler.applyBonus(totalPrize, appliedBonusSymbol);
        return totalPrize;
    }

    /**
     * Calculates the prize for a specific symbol based on its winning combinations.
     *
     * @param betAmount   the amount the user has bet
     * @param symbol      the symbol for which the prize is being calculated
     * @param combinations the list of winning combinations applied to the symbol
     * @return the calculated prize for the symbol
     */
    private double calculateSymbolPrize(double betAmount, String symbol, List<String> combinations) {
        double symbolPrize = 0;

        for (String combination : combinations) {
            SlotSymbol slotSymbol = gameConfiguration.symbols().get(symbol);
            validateSymbol(slotSymbol);

            if (isSameSymbolsWinCombination(combination)) {
                symbolPrize += calculateSameSymbolsPrize(betAmount, slotSymbol, symbol, combination);
            } else {
                symbolPrize *= getRewardMultiplier(combination);
            }
        }

        return symbolPrize;
    }

    /**
     * Validates whether the given symbol is a standard symbol.
     * Throws an exception if the symbol is not of type STANDARD.
     *
     * @param slotSymbol the slot symbol to validate
     */
    private void validateSymbol(SlotSymbol slotSymbol) {
        if (slotSymbol.getType() != SymbolTypeEnum.STANDARD) {
            throw new RuntimeException("StandardSymbol not found in symbols");
        }
    }

    /**
     * Checks if a given winning combination is a 'same symbols' combination.
     *
     * @param combination the winning combination to check
     * @return true if it is a 'same symbols' combination, false otherwise
     */
    private boolean isSameSymbolsWinCombination(String combination) {
        return gameConfiguration.winCombinations().get(combination).getWhen() == WhenEnum.SAME_SYMBOLS;
    }

    /**
     * Calculates the prize for a 'same symbols' combination.
     * It multiplies the bet amount by the symbol's reward multiplier and the combination value.
     *
     * @param betAmount   the amount the user has bet
     * @param slotSymbol  the slot symbol for which the prize is being calculated
     * @param symbol      the symbol key
     * @param combination the combination applied to the symbol
     * @return the calculated prize for the 'same symbols' combination
     */
    private double calculateSameSymbolsPrize(double betAmount, SlotSymbol slotSymbol, String symbol, String combination) {
        StandardSymbol standardSymbol = (StandardSymbol) slotSymbol;
        double baseMultiplier = betAmount * standardSymbol.getRewardMultiplier();
        double combinationValue = getCombinationValue(combination);
        return baseMultiplier * combinationValue;
    }

    /**
     * Retrieves the reward multiplier for a specific combination.
     *
     * @param combination the combination key
     * @return the reward multiplier for the combination
     */
    private double getRewardMultiplier(String combination) {
        return gameConfiguration.winCombinations().get(combination).getRewardMultiplier();
    }

    /**
     * Retrieves the combination value (reward multiplier) for a given winning combination.
     *
     * @param combination the combination key
     * @return the reward multiplier for the combination
     * @throws RuntimeException if the combination is not found in the configuration
     */
    private double getCombinationValue(String combination) {
        if (!gameConfiguration.winCombinations().containsKey(combination)) {
            throw new RuntimeException("WinCombination doesn't exist in WinCombinations");
        }
        WinningCombination winning = gameConfiguration.winCombinations().get(combination);
        return winning.getRewardMultiplier(); // Return the multiplier for the combination
    }

    /**
     * Finds the applied bonus symbol from the provided matrix.
     * It scans the matrix for any bonus symbol that is not a 'miss' symbol.
     *
     * @param matrix a 2D array representing the game matrix
     * @return the applied bonus symbol, or null if no bonus symbol is found
     */
    public String findAppliedBonusSymbol(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                String symbol = matrix[row][column];
                if (symbolUtils.isBonusSymbol(symbol) && !symbolUtils.isBonusMissSymbol(symbol)) {
                    return symbol;
                }
            }
        }
        return null; // Returns null if no bonus symbol is found
    }
}