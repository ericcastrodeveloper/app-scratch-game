package com.cyberspeed.domain.validator.area;

import com.cyberspeed.domain.model.winning.WinningAreaCombination;
import com.cyberspeed.utils.SymbolUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * This class validates specific areas in a game matrix to determine if they meet
 * the criteria for a winning combination.
 */
public class AreaValidator {
    private static final Logger logger = Logger.getLogger(AreaValidator.class.getName());


    private SymbolUtils symbolUtils;

    /**
     * Constructor to initialize the AreaValidator with a SymbolUtils instance.
     *
     * @param symbolUtils an instance of SymbolUtils for symbol-related operations
     */
    public AreaValidator(SymbolUtils symbolUtils) {
        this.symbolUtils = symbolUtils;
    }

    /**
     * Validates a specific area in the matrix based on the winning area combination.
     *
     * @param key                    the identifier for the area being validated
     * @param winningAreaCombination  the winning area combination to validate against
     * @param matrix                 a 2D array representing the symbols in the matrix
     * @return a map of winning area combinations where the key is the symbol and
     *         the value is a list of area identifiers that match
     */
    public Map<String, List<String>> validateArea(String key, WinningAreaCombination winningAreaCombination, String[][] matrix) {
        String[][] coveredAreas = winningAreaCombination.getCoveredAreas();
        Map<String, List<String>> winningAreaCombinations = new HashMap<>();

        // Validate if the matrix size matches the covered areas size
        if (matrix.length != coveredAreas[0].length) {
            logger.warning("It is not possible to validate linear_symbols the covered area size is different from matrix size");
            return winningAreaCombinations;
        }

        for (int row = 0; row < coveredAreas.length; row++) {
            boolean isMatched = validateSingleArea(coveredAreas[row], matrix);
            if (isMatched) {
                String symbol = getSymbolAtPosition(coveredAreas[row][0], matrix);
                winningAreaCombinations.put(symbol, Collections.singletonList(key));
            }
        }

        return winningAreaCombinations;
    }

    /**
     * Validates a single area against the matrix to check if it matches a standard symbol.
     *
     * @param area   the coordinates of the area to validate
     * @param matrix a 2D array representing the symbols in the matrix
     * @return true if the area matches a standard symbol, false otherwise
     */
    private boolean validateSingleArea(String[] area, String[][] matrix) {
        String symbol = getSymbolAtPosition(area[0], matrix);

        if(!symbolUtils.isStandardSymbol(symbol)) {
            return false;
        }

        for (int i = 1; i < area.length; i++) {
            String currentSymbol = getSymbolAtPosition(area[i], matrix);
            if (!symbol.equals(currentSymbol)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the symbol at a given coordinate in the matrix.
     *
     * @param coordinate the coordinate in the format "row:column"
     * @param matrix     a 2D array representing the symbols in the matrix
     * @return the symbol at the specified position
     */
    private String getSymbolAtPosition(String coordinate, String[][] matrix) {
        int[] position = convertCoordinateToPosition(coordinate);
        return matrix[position[0]][position[1]];
    }

    /**
     * Converts a coordinate string to an integer array representing matrix indices.
     *
     * @param coordinate the coordinate in the format "row:column"
     * @return an array of integers where the first element is the row and the second is the column
     */
    private int[] convertCoordinateToPosition(String coordinate) {
        String[] position = coordinate.split(":");
        int[] result = new int[position.length];
        for (int i = 0; i < position.length; i++) {
            result[i] = Integer.parseInt(position[i]);
        }
        return result;
    }
}
