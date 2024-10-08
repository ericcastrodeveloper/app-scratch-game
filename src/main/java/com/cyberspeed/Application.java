package com.cyberspeed;

import com.cyberspeed.infraestructure.application.GameLauncher;

/**
 * The {@code Application} class is the entry point of the program.
 *
 * <p>This class contains the {@code main} method, which is the starting point of the application.
 * It delegates the initialization of the application to the {@link GameLauncher} class by passing
 * the command-line arguments to its {@code init} method.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     java -jar your-application.jar --config config.json --betting-amount 100
 * </pre>
 */
public class Application {
    public static void main(String[] args) {
        GameLauncher.init(args);
    }
}