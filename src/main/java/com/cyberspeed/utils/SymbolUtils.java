package com.cyberspeed.utils;

import com.cyberspeed.domain.enums.ImpactEnum;
import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.domain.enums.SymbolTypeEnum;
import com.cyberspeed.domain.model.symbols.bonus.BonusSymbol;

import java.util.Collections;
import java.util.Map;

/**
 * Utility class to provide helper methods for symbol-related operations.
 * This class primarily works with standard and bonus symbols.
 */
public class SymbolUtils {

    private final Map<String, SlotSymbol> symbols;

    /**
     * Constructs a SymbolUtils instance with the provided symbol map.
     * The map is wrapped to be unmodifiable to prevent external modifications.
     *
     * @param symbols a map of symbol keys to SlotSymbol objects
     */
    public SymbolUtils(Map<String, SlotSymbol> symbols) {
        this.symbols = Collections.unmodifiableMap(symbols);
    }

    /**
     * Checks if the given symbol key corresponds to a standard symbol.
     *
     * @param symbol the key representing a symbol
     * @return true if the symbol is a standard symbol, false otherwise
     * @throws IllegalArgumentException if the symbol is null or empty
     */
    public boolean isStandardSymbol(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }

        // Check if the symbol exists in the map and if its type is STANDARD
        return symbols.containsKey(symbol) && SymbolTypeEnum.STANDARD.equals(symbols.get(symbol).getType());
    }

    /**
     * Checks if the given symbol key corresponds to a bonus symbol.
     *
     * @param symbol the key representing a symbol
     * @return true if the symbol is a standard symbol, false otherwise
     * @throws IllegalArgumentException if the symbol is null or empty
     */
    public boolean isBonusSymbol(String symbol) {
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }

        // Check if the symbol exists in the map and if its type is BONUS
        return symbols.containsKey(symbol) && SymbolTypeEnum.BONUS.equals(symbols.get(symbol).getType());
    }

    public boolean isBonusMissSymbol(String symbol) {
        BonusSymbol bonusSymbol = null;
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be null or empty");
        }
        if(isBonusSymbol(symbol)) {
            bonusSymbol = (BonusSymbol) symbols.get(symbol);
        }
        // Check if the symbol exists in the map and if its type is BONUS
        return bonusSymbol != null && bonusSymbol.getImpact().equals(ImpactEnum.MISS);
    }

}
