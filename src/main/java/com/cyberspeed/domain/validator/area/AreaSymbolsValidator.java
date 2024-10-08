package com.cyberspeed.domain.validator.area;

import com.cyberspeed.domain.model.winning.WinningAreaCombination;
import com.cyberspeed.domain.model.winning.WinningCombination;
import com.cyberspeed.domain.validator.SymbolsValidator;
import com.cyberspeed.domain.enums.WhenEnum;
import com.cyberspeed.utils.MapUtils;

import java.util.*;

/**
 * The AreaSymbolsValidator class implements the Validator interface
 * and is responsible for validating winning combinations in a specified area
 * of the matrix based on the defined winning rules.
 */
public class AreaSymbolsValidator implements SymbolsValidator {

    private final Map<String, WinningCombination> winCombinations;
    private final AreaValidator areaValidator;

    /**
     * Constructor to initialize the AreaSymbolsValidator with winning combinations and area validator.
     *
     * @param winCombinations a map of winning combinations associated with keys
     * @param areaValidator an instance of AreaValidator for validating areas
     */
    public AreaSymbolsValidator(Map<String, WinningCombination> winCombinations, AreaValidator areaValidator) {
        this.winCombinations = winCombinations;
        this.areaValidator = areaValidator;
    }

    /**
     * Validates the matrix for winning combinations in specified areas.
     *
     * @param matrix the matrix to be validated
     * @return a map containing symbols and their associated winning combinations found in the area
     */
    @Override
    public Map<String, List<String>> validate(String[][] matrix) {
        Map<String, List<String>> areaWinningCombination = new HashMap<>();

        for (Map.Entry<String, WinningCombination> entry : winCombinations.entrySet()) {
            String key = entry.getKey();
            WinningCombination winCombination = entry.getValue();

            if (winCombination.getWhen().equals(WhenEnum.LINEAR_SYMBOLS)) {
                WinningAreaCombination winningAreaCombination = (WinningAreaCombination) winCombination;
                Map<String, List<String>> validatedArea = areaValidator.validateArea(key, winningAreaCombination, matrix);

                areaWinningCombination = MapUtils.mergeMaps(areaWinningCombination, validatedArea);
            }
        }

        return areaWinningCombination;
    }
}
