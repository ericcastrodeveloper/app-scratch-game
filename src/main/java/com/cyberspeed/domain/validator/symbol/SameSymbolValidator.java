package com.cyberspeed.domain.validator.symbol;

import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.domain.model.winning.WinningCombination;
import com.cyberspeed.domain.validator.SymbolsValidator;
import com.cyberspeed.domain.enums.WhenEnum;
import com.cyberspeed.domain.mapper.SymbolWinningMapper;
import com.cyberspeed.utils.SymbolUtils;

import java.util.List;
import java.util.Map;

/**
 * The SameSymbolValidator class implements the Validator interface
 * and is responsible for validating the matrix for winning combinations
 * based on the occurrence of repeated symbols.
 */
public class SameSymbolValidator implements SymbolsValidator {
    private final Map<String, WinningCombination> winCombinations;
    private final Map<String, SlotSymbol> symbols;
    private final SymbolCounter symbolCounter;
    private final SymbolWinningMapper symbolWinningMapper;

    /**
     * Constructor to initialize the SameSymbolValidator with necessary dependencies.
     *
     * @param winCombinations a map of winning combinations keyed by symbol identifiers
     * @param symbols         a map of slot symbols keyed by symbol identifiers
     * @param symbolUtils     a utility class for symbol-related operations
     */
    public SameSymbolValidator(Map<String, WinningCombination> winCombinations, Map<String, SlotSymbol> symbols, SymbolUtils symbolUtils) {
        this.winCombinations = winCombinations;
        this.symbols = symbols;
        this.symbolCounter = new SymbolCounter(symbols, symbolUtils);
        this.symbolWinningMapper = new SymbolWinningMapper(winCombinations);
    }


    /**
     * Validates the matrix for repeated symbols and maps them to their winning combinations.
     *
     * @param matrix the matrix to be validated
     * @return a map containing symbols and their associated winning combinations found in the matrix
     */
    @Override
    public Map<String, List<String>> validate(String[][] matrix) {
        Map<String, Integer> repeatedSymbolCounts = symbolCounter.countStandardSymbolsInMatrix(matrix);
        return symbolWinningMapper.mapSymbolsToWinningCombinations(repeatedSymbolCounts, WhenEnum.SAME_SYMBOLS);
    }
}
