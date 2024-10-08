package com.cyberspeed.utils;

import java.util.Random;

/**
 * Utility class for generating random numbers.
 * Provides methods to generate random integers within specified bounds.
 */
public class RandomGenerator {
    private Random random;

    /**
     * Constructs a new RandomGenerator with a default Random instance.
     */
    public RandomGenerator() {
        this.random = new Random();
    }


    /**
     * Returns a random integer between 1 (inclusive) and the specified bound (inclusive).
     *
     * @param bound the upper bound for the random number (must be greater than 0)
     * @return a random integer between 1 and bound (inclusive)
     */
    public Integer getRandomInteger(int bound) {
        return random.nextInt(bound) + 1;
    }
}
