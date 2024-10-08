package com.cyberspeed.domain.model.winning;

import com.cyberspeed.domain.enums.WhenEnum;
import com.cyberspeed.domain.enums.WinGroupEnum;

/**
 * Represents a winning count combination in the game.
 * <p>
 * This class extends the WinningCombination class and includes the count of the same symbols to activate the reward.
 * </p>
 */
public class WinningCountCombination extends WinningCombination {
    private final Integer count; // Required count of the same symbols to activate the reward

    /**
     * Constructor to initialize the WinningCountCombination with its properties.
     *
     * @param count            the count of the same symbols to activate the reward
     * @param rewardMultiplier  the multiplier for the reward
     * @param when             the condition for the winning combination
     * @param group            the group associated with the winning combination
     */
    public WinningCountCombination(Integer count, double rewardMultiplier, WhenEnum when, WinGroupEnum group) {
        super(rewardMultiplier, when, group);
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "WinningCountCombination{" +
                super.toString() + // Include parent variables
                ", count=" + count +
                '}';
    }
}
