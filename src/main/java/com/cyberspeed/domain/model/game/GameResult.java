package com.cyberspeed.domain.model.game;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Represents the result of a game session, including the game matrix,
 * reward amount, applied winning combinations, and any bonus symbols applied.
 *
 * @param matrix                     2D array representing the game matrix
 * @param reward                     Reward earned from the game
 * @param appliedWinningCombinations Winning combinations applied to symbols
 * @param appliedBonusSymbol         Any bonus symbol applied during the game
 */
public record GameResult(String[][] matrix, double reward, Map<String, List<String>> appliedWinningCombinations,
                         String appliedBonusSymbol) {
    /**
     * Constructor to initialize the GameResult with the specified parameters.
     *
     * @param matrix                     the game matrix
     * @param reward                     the reward amount
     * @param appliedWinningCombinations the winning combinations applied
     * @param appliedBonusSymbol         the bonus symbol applied
     */
    public GameResult {
    }


    @Override
    public String toString() {
        return "GameResult{" +
                "matrix=" + Arrays.toString(matrix) +
                ", reward=" + reward +
                ", appliedWinningCombinations=" + appliedWinningCombinations +
                ", appliedBonusSymbol='" + appliedBonusSymbol + '\'' +
                '}';
    }
}
