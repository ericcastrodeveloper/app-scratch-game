package com.cyberspeed.domain.model.symbols.standard;

import com.cyberspeed.domain.enums.SymbolTypeEnum;
import com.cyberspeed.domain.model.symbols.SlotSymbol;

/**
 * Represents a standard symbol in the game.
 * <p>
 * A StandardSymbol has a type and a reward multiplier that enhances the rewards
 * based on the game rules.
 * </p>
 */
public class StandardSymbol extends SlotSymbol {


    /**
     * Constructor to initialize the SlotSymbol with a specific type.
     *
     * @param type the type of the slot symbol
     */
    public StandardSymbol(double rewardMultiplier, SymbolTypeEnum type) {
        super(rewardMultiplier, type);
    }

    @Override
    public String toString() {
        return "StandardSymbol{}" + super.toString();
    }
}
