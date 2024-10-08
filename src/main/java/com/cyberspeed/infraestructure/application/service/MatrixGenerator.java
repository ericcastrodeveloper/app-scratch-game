package com.cyberspeed.infraestructure.application.service;

import com.cyberspeed.domain.service.SelectorSymbol;
import com.cyberspeed.utils.SymbolUtils;

/**
 * The MatrixGenerator class is responsible for generating a game matrix filled
 * with symbols by utilizing a SelectorSymbol to select symbols for each cell
 * based on the specified dimensions.
 */
public class MatrixGenerator {

    private final SelectorSymbol selectorSymbol;
    private final SymbolUtils symbolUtils;

    /**
     * Constructs a MatrixGenerator with the specified SelectorSymbol.
     *
     * @param selectorSymbol the SelectorSymbol used to select symbols for the matrix
     */
    public MatrixGenerator(SelectorSymbol selectorSymbol, SymbolUtils symbolUtils) {
        this.selectorSymbol = selectorSymbol;
        this.symbolUtils = symbolUtils;
    }

    /**
     * Generates a 2D array representing the game matrix filled with symbols
     * selected by the SelectorSymbol.
     *
     * @param rows    the number of rows in the matrix
     * @param columns the number of columns in the matrix
     * @return a 2D array of strings representing the generated game matrix
     */
    public String[][] generateMatrix(int rows, int columns) {
        String[][] matrix = new String[rows][columns];
        boolean includedBonus = false;

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                matrix[j][i] = selectSymbolForCell(j, i, includedBonus);
                if (symbolUtils.isBonusSymbol(matrix[j][i])) {
                    includedBonus = true;
                }
            }
        }
        return matrix;
    }

    /**
     * Selects a symbol for the specified cell.
     *
     * @param row            the row index
     * @param column         the column index
     * @param bonusIncluded  whether a bonus symbol has already been included
     * @return the key of the selected symbol
     */
    private String selectSymbolForCell(int row, int column, boolean bonusIncluded) {
        boolean withBonus = !bonusIncluded;
        return selectorSymbol.selectRandomSymbolKey(row, column, withBonus);
    }

}