package com.cyberspeed.domain.adapters;

import com.cyberspeed.domain.model.winning.WinningAreaCombination;
import com.cyberspeed.domain.model.winning.WinningCountCombination;
import com.cyberspeed.domain.enums.WhenEnum;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Adapter class for deserializing JSON representations of WinningCombination.
 * <p>
 * This class implements the {@link JsonDeserializer} interface to provide
 * custom deserialization logic for WinningCombination objects from JSON.
 * It determines the appropriate subclass (WinningCountCombination or WinningAreaCombination)
 * based on the "when" field in the JSON.
 * </p>
 */
public class WinningCombinationAdapter implements JsonDeserializer {
    /**
     * Deserializes a JSON element into a WinningCombination object.
     *
     * @param jsonElement the JSON element to deserialize
     * @param type        the type of the object to deserialize into
     * @param context     the deserialization context
     * @return the deserialized WinningCombination object
     * @throws JsonParseException if the JSON does not represent a valid WinningCombination
     */
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        // Extracts when value from the JSON object
        String whenValue = jsonObject.get("when").getAsString();

        // Checks if the type is SAME_SYMBOLS or LINEAR_SYMBOLS and deserializes to the appropriate class
        if (isSameSymbol(whenValue))
            return context.deserialize(jsonElement, WinningCountCombination.class);
        else if (isLinearSymbol(whenValue))
            return context.deserialize(jsonElement, WinningAreaCombination.class);
        else {
            // Throws a parsing exception for invalid symbol types
            throw new JsonParseException("Invalid when value: " + whenValue);
        }
    }

    /**
     * Checks if the specified when value corresponds to the same symbols.
     *
     * @param whenValue when value to check
     * @return true when value is SAME_SYMBOLS, false otherwise
     */
    private static boolean isSameSymbol(String whenValue) {
        return WhenEnum.SAME_SYMBOLS.getValue().equals(whenValue);
    }

    /**
     * Checks if the specified when value corresponds to linear symbols.
     *
     * @param whenValue when value to check
     * @return true when value is LINEAR_SYMBOLS, false otherwise
     */
    private static boolean isLinearSymbol(String whenValue) {
        return WhenEnum.LINEAR_SYMBOLS.getValue().equals(whenValue);
    }
}
