package com.cyberspeed.infraestructure.output;

import com.cyberspeed.domain.model.game.GameResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The ConsoleResultHandler class implements the ResultHandler interface to print game results to the console.
 */
public class ConsoleResultHandler implements ResultHandler {

    @Override
    public void handleResult(GameResult gameResult) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(gameResult);
        System.out.println(result);
    }
}
