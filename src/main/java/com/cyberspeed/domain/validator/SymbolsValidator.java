package com.cyberspeed.domain.validator;

import java.util.List;
import java.util.Map;

/**
 * The Validator interface defines a contract for validating matrices
 * based on specific winning rules or conditions in a game.
 * Implementing classes must provide the validation logic for different types
 * of validations related to symbols and winning combinations.
 */
public interface SymbolsValidator {
    /**
     * Validates the given matrix against specific rules and conditions.
     *
     * @param matrix the matrix to be validated
     * @return a map containing the results of the validation,
     *         including symbols and their associated winning combinations
     */
    Map<String, List<String>> validate(String[][] matrix);
}
