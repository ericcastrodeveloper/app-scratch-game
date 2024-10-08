package com.cyberspeed.infraestructure.application.service;

import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.domain.model.game.GameResult;
import com.cyberspeed.domain.validator.area.AreaSymbolsValidator;
import com.cyberspeed.domain.validator.area.AreaValidator;
import com.cyberspeed.domain.validator.symbol.SameSymbolValidator;
import com.cyberspeed.utils.MapUtils;
import com.cyberspeed.utils.SymbolUtils;

import java.util.List;
import java.util.Map;

/**
 * The GameValidator class is responsible for validating the game matrix
 * and producing the game result based on specified validation rules.
 */
public class GameValidator {
    private final GameConfiguration gameConfiguration;
    private final SymbolUtils symbolUtils;
    private final GameReward gameReward;

    /**
     * Constructs a GameValidator instance with the specified game configuration
     * and symbol utility.
     *
     * @param gameConfiguration the game configuration containing symbols and
     *                         winning combinations
     * @param symbolUtils      utility class for symbol-related operations
     */
    public GameValidator(GameConfiguration gameConfiguration, SymbolUtils symbolUtils) {
        this.gameConfiguration = gameConfiguration;
        this.symbolUtils = symbolUtils;
        this.gameReward = new GameReward(gameConfiguration, symbolUtils); // Initializing GameReward
    }

    /**
     * Validates the provided game matrix and returns the result.
     *
     * @param betAmount
     * @param matrix    a 2D array representing the game matrix
     * @return the game result containing the matrix, prize, winning combinations,
     * and applied bonus symbol
     */
    public GameResult validate(double betAmount, String[][] matrix) {
        // Validate repeated symbols
        Map<String, List<String>> repeatedSymbolWinningCombination = validateRepeatedSymbols(matrix);

        // Validate area symbols
        Map<String, List<String>> areaWinningCombination = validateAreaSymbols(matrix);

        // Merging winning combinations
        Map<String, List<String>> winningCombination = MapUtils.mergeMaps(repeatedSymbolWinningCombination, areaWinningCombination);

        // Return early if there are no winning combinations
        if(winningCombination.isEmpty()){
            return new GameResult(matrix, 0.00, winningCombination, null);
        }

        // Calculate prize and find applied bonus symbol
        String appliedBonusSymbol = gameReward.findAppliedBonusSymbol(matrix);
        double reward = gameReward.calculatePrize(betAmount, winningCombination, appliedBonusSymbol);

        return new GameResult(matrix, reward, winningCombination, appliedBonusSymbol);
    }

    private Map<String, List<String>> validateRepeatedSymbols(String[][] matrix) {
        SameSymbolValidator sameSymbolValidator = new SameSymbolValidator(gameConfiguration.winCombinations(), gameConfiguration.symbols(), symbolUtils);
        return sameSymbolValidator.validate(matrix);
    }

    private Map<String, List<String>> validateAreaSymbols(String[][] matrix) {
        AreaValidator areaValidator = new AreaValidator(symbolUtils);
        AreaSymbolsValidator areaSymbolsValidator = new AreaSymbolsValidator(gameConfiguration.winCombinations(), areaValidator);
        return areaSymbolsValidator.validate(matrix);
    }
}