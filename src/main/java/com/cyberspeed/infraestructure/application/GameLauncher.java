package com.cyberspeed.infraestructure.application;

import com.cyberspeed.config.AppConfiguration;
import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.domain.model.game.GameResult;
import com.cyberspeed.domain.service.ProbabilityAggregator;
import com.cyberspeed.domain.service.ProbabilityBonusService;
import com.cyberspeed.domain.service.ProbabilityStandardService;
import com.cyberspeed.domain.service.SelectorSymbol;
import com.cyberspeed.infraestructure.application.exceptions.InvalidParametersException;
import com.cyberspeed.infraestructure.application.service.GameValidator;
import com.cyberspeed.infraestructure.application.service.MatrixGenerator;
import com.cyberspeed.infraestructure.application.validator.ParameterValidator;
import com.cyberspeed.infraestructure.output.ConsoleResultHandler;
import com.cyberspeed.infraestructure.output.ResultHandler;
import com.cyberspeed.utils.RandomGenerator;
import com.cyberspeed.utils.SymbolUtils;

import java.util.logging.Logger;

/**
 * The GameLauncher class is responsible for initializing and launching the game.
 * It handles the loading of configurations, sets up services, runs the game logic, and prints the results.
 */
public class GameLauncher {
    private static final Logger logger = Logger.getLogger(GameLauncher.class.getName());
    private static final int CONFIG_FILE_INDEX = 1;
    private static final int BETTING_AMOUNT_INDEX = 3;

    /**
     * Initializes the game by loading configuration, setting up probability services, and running the game.
     */
    public static void init(String[] args) {
        try {
            ParameterValidator.validate(args);

            double bettingAmount = Double.parseDouble(args[BETTING_AMOUNT_INDEX]);
            String configFile = args[CONFIG_FILE_INDEX];

            // Load the game configuration
            GameConfiguration gameConfiguration = loadGameConfiguration(configFile);

            // Build and aggregate probabilities
            ProbabilityAggregator probabilityAggregator = buildProbabilityAggregator(gameConfiguration);

            // Run the game logic
            GameResult gameResult = runGame(bettingAmount, gameConfiguration, probabilityAggregator);

            // Print the final game result
            ResultHandler resultHandler = new ConsoleResultHandler();
            resultHandler.handleResult(gameResult);

        } catch (Exception e) {
            // Handle initialization errors
            handleInitializationError(e);
        }
    }

    /**
     * Validates the input parameters to ensure they are valid for game initialization.
     *
     * @param args the input arguments for the game, where the first argument is the betting amount
     *             and the second argument is the optional config file.
     * @throws InvalidParametersException if the parameters are invalid.
     */
    private static void validateParameters(String[] args) {
        if (args.length < 3) {
            throw new InvalidParametersException("No parameters provided. Please provide the betting amount and optionally a config file.");
        }

        try {
            double bettingAmount = Double.parseDouble(args[3]);
            if (bettingAmount <= 0) {
                throw new InvalidParametersException("Invalid betting amount. It must be a positive number.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidParametersException("Betting amount must be a valid number.");
        }
    }

    /**
     * Loads the game configuration from the configuration file.
     *
     * @param configFile the name of the configuration file.
     * @return GameConfiguration object containing game settings and probabilities.
     * @throws Exception if there is an error during the loading of the configuration.
     */
    private static GameConfiguration loadGameConfiguration(String configFile) {
        AppConfiguration appConfiguration = new AppConfiguration();
        return appConfiguration.loadConfiguration(configFile);
    }

    /**
     * Builds the ProbabilityAggregator by initializing bonus and standard probability services.
     *
     * @param gameConfiguration the loaded game configuration containing probabilities.
     * @return a ProbabilityAggregator instance containing both standard and bonus probability services.
     */
    private static ProbabilityAggregator buildProbabilityAggregator(GameConfiguration gameConfiguration) {
        ProbabilityBonusService probabilityBonusService = new ProbabilityBonusService(gameConfiguration.probabilities().bonusSymbolProbabilities());
        ProbabilityStandardService probabilityStandardService = new ProbabilityStandardService(gameConfiguration.probabilities().standardSymbolProbabilities());
        return new ProbabilityAggregator(probabilityStandardService, probabilityBonusService);
    }

    /**
     * Runs the game logic, generating the matrix, validating symbols, and computing the results.
     *
     * @param betAmount the amount bet by the player.
     * @param gameConfiguration the loaded game configuration.
     * @param probabilityAggregator aggregator that manages symbol probabilities.
     * @return a GameResult object containing the final game state, winning combinations, and rewards.
     */
    private static GameResult runGame(double betAmount, GameConfiguration gameConfiguration, ProbabilityAggregator probabilityAggregator) {
        // Selector for choosing symbols based on aggregated probabilities
        SelectorSymbol selectorSymbol = new SelectorSymbol(probabilityAggregator, new RandomGenerator());

        // Utility class for validate if is a standard symbol used in SymbolCounter and AreaValidator
        SymbolUtils symbolUtils = new SymbolUtils(gameConfiguration.symbols());

        // Generates a matrix (grid) for the game
        MatrixGenerator matrixGenerator = new MatrixGenerator(selectorSymbol, symbolUtils);
        String[][] matrix = matrixGenerator.generateMatrix(gameConfiguration.rows(), gameConfiguration.columns());

        // Validates the game matrix and calculates winning combinations and bonus
        GameValidator gameValidator = new GameValidator(gameConfiguration, symbolUtils);
        return gameValidator.validate(betAmount, matrix);
    }

    /**
     * Handles any errors that occur during game initialization, logging the error.
     *
     * @param e the Exception encountered during initialization.
     */
    private static void handleInitializationError(Exception e) {
        logger.severe("Error during initialization: " + e.getMessage());
    }
}