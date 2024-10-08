package com.cyberspeed.domain.adapters;

import com.cyberspeed.domain.model.symbols.bonus.BonusSymbol;
import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.domain.model.symbols.standard.StandardSymbol;
import com.cyberspeed.domain.enums.SymbolTypeEnum;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Adapter class for deserializing JSON representations of SlotSymbol.
 * <p>
 * This class implements the {@link JsonDeserializer} interface to provide
 * custom deserialization logic for SlotSymbol objects from JSON.
 * It determines the appropriate subclass (BonusSymbol or StandardSymbol)
 * based on the "type" field in the JSON.
 * </p>
 */
public class SlotSymbolAdapter implements JsonDeserializer<SlotSymbol> {
    /**
     * Deserializes a JSON element into a SlotSymbol object.
     *
     * @param jsonElement the JSON element to deserialize
     * @param type       the type of the object to deserialize into
     * @param context    the deserialization context
     * @return the deserialized SlotSymbol object
     * @throws JsonParseException if the JSON does not represent a valid SlotSymbol
     */
    @Override
    public SlotSymbol deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Extracts the symbol type from the JSON object
        String symbolType = jsonObject.get("type").getAsString();

        // Checks if the type is BONUS or STANDARD and deserializes to the appropriate class
        if (isBonusType(symbolType)) {
            return context.deserialize(jsonElement, BonusSymbol.class);
        } else if (isStandardType(symbolType)) {
            return context.deserialize(jsonElement, StandardSymbol.class);
        } else {
            // Throws a parsing exception for invalid symbol types
            throw new JsonParseException("Invalid symbol type: " + symbolType);
        }
    }

    /**
     * Checks if the specified symbol type is a bonus type.
     *
     * @param symbolType the symbol type to check
     * @return true if the symbol type is BONUS, false otherwise
     */
    private static boolean isBonusType(String symbolType) {
        return SymbolTypeEnum.BONUS.getValue().equals(symbolType);
    }

    /**
     * Checks if the specified symbol type is a standard type.
     *
     * @param symbolType the symbol type to check
     * @return true if the symbol type is STANDARD, false otherwise
     */
    private boolean isStandardType(String symbolType) {
        return SymbolTypeEnum.STANDARD.getValue().equals(symbolType);
    }


}
