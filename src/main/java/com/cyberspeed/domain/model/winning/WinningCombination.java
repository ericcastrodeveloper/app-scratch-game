package com.cyberspeed.domain.model.winning;

import com.cyberspeed.domain.enums.WinGroupEnum;
import com.cyberspeed.domain.enums.WhenEnum;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a winning combination in the game.
 * <p>
 * This class contains details about the winning combination, including its reward multiplier,
 * the condition under which it applies, and the group to which it belongs.
 * </p>
 */
public class WinningCombination {
    @SerializedName("reward_multiplier")
    private final double rewardMultiplier; // The multiplier for the reward associated with this winning combination

    @SerializedName("when")
    private final WhenEnum when; // The condition under which this winning combination applies

    @SerializedName("group")
    private final WinGroupEnum group; // The group to which this winning combination belongs

    /**
     * Constructor to initialize the WinningCombination with its properties.
     *
     * @param rewardMultiplier the multiplier for the reward
     * @param when             the condition for the winning combination
     * @param group            the group associated with the winning combination
     */
    public WinningCombination(double rewardMultiplier, WhenEnum when, WinGroupEnum group) {
        this.rewardMultiplier = rewardMultiplier;
        this.when = when;
        this.group = group;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public WhenEnum getWhen() {
        return when;
    }

    public WinGroupEnum getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "WinningCombination{" +
                "rewardMultiplier=" + rewardMultiplier +
                ", when=" + when +
                ", group=" + group +
                '}';
    }
}
