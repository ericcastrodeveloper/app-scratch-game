package com.cyberspeed.infraestructure.output;

import com.cyberspeed.domain.model.game.GameResult;

/**
 * The ResultHandler interface defines a contract for handling game results.
 * Implementations of this interface can define how the game results are processed or stored.
 */
public interface ResultHandler {
    /**
     * Processes the game result, whether it is printed, saved, or sent elsewhere.
     *
     * @param gameResult The result of the game to be processed.
     */
    void handleResult(GameResult gameResult);
}