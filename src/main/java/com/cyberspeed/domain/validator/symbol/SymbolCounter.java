package com.cyberspeed.domain.validator.symbol;

import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.utils.SymbolUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for counting the occurrences of standard symbols
 * in a given matrix of symbols.
 */
public class SymbolCounter {
    private final Map<String, SlotSymbol> symbols;
    private final SymbolUtils symbolUtils;

    /**
     * Constructor to initialize the SymbolCounter with the required symbols and utility.
     *
     * @param symbols a map of symbols to be counted
     * @param symbolUtils utility class for symbol operations
     */
    public SymbolCounter(Map<String, SlotSymbol> symbols, SymbolUtils symbolUtils) {
        this.symbols = symbols;
        this.symbolUtils = symbolUtils;
    }

    /**
     * Counts the occurrences of standard symbols in the provided matrix.
     *
     * @param matrix a 2D array representing the symbols in the matrix
     * @return a map containing the counts of each standard symbol found
     */
    public Map<String, Integer> countStandardSymbolsInMatrix(String[][] matrix) {
        Map<String, Integer> symbolCount = new HashMap<>();

        for (String symbol : symbols.keySet()) {
            if(symbolUtils.isStandardSymbol(symbol)) {
                int count = countSymbols(matrix, symbol);
                if (count > 0) {
                    symbolCount.put(symbol, count);
                }
            }
        }
        return symbolCount;
    }

    /**
     * Counts the occurrences of a specific symbol in the matrix.
     *
     * @param matrix a 2D array representing the symbols in the matrix
     * @param symbol the symbol to count
     * @return the number of occurrences of the symbol in the matrix
     */
    private static int countSymbols(String[][] matrix, String symbol) {
        int count = 0;
        for (String[] row : matrix) {
            for (String cell : row) {
                if (cell.equals(symbol)) {
                    count++;
                }
            }
        }
        return count;
    }
}
