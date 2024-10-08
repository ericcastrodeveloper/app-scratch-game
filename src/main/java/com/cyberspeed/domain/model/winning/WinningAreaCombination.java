package com.cyberspeed.domain.model.winning;

import com.cyberspeed.domain.enums.WhenEnum;
import com.cyberspeed.domain.enums.WinGroupEnum;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

/**
 * Represents a winning area combination in the game.
 * <p>
 * This class extends the WinningCombination class and includes information about
 * the specific areas that are covered in a winning combination.
 * </p>
 */
public class WinningAreaCombination extends WinningCombination {
    @SerializedName("covered_areas")
    private final String[][] coveredAreas; // Represents the areas covered in the winning combination

    /**
     * Constructor to initialize the WinningAreaCombination with its properties.
     *
     * @param coveredAreas      a 2D array representing the areas covered in the winning combination
     * @param rewardMultiplier  the multiplier for the reward
     * @param when             the condition for the winning combination
     * @param group            the group associated with the winning combination
     */
    public WinningAreaCombination(String[][] coveredAreas, double rewardMultiplier, WhenEnum when, WinGroupEnum group) {
        super(rewardMultiplier, when, group);
        this.coveredAreas = coveredAreas;
    }

    public String[][] getCoveredAreas() {
        return coveredAreas;
    }

    @Override
    public String toString() {
        return "WinningAreaCombination{" +
                super.toString() + // Include parent variables
                ", coveredAreas=" + Arrays.deepToString(coveredAreas) + // Using deepToString for 2D array
                '}';
    }
}