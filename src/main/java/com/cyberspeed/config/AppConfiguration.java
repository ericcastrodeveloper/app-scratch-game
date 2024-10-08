package com.cyberspeed.config;

import com.cyberspeed.domain.model.symbols.SlotSymbol;
import com.cyberspeed.domain.model.winning.WinningCombination;
import com.cyberspeed.domain.adapters.SlotSymbolAdapter;
import com.cyberspeed.domain.adapters.WinningCombinationAdapter;
import com.cyberspeed.shared.exceptions.ConfigurationException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The AppConfiguration class is responsible for loading and parsing the
 * game configuration from a JSON file. It uses the Gson library to
 * deserialize the JSON into a GameConfiguration object.
 */
public class AppConfiguration {
    private static final String DEFAULT_FILE_NAME = "/config.json"; // Default configuration file
    private Gson gson;

    public AppConfiguration() {
        gson = new GsonBuilder()
                .registerTypeAdapter(SlotSymbol.class, new SlotSymbolAdapter()) // Custom deserializer for SlotSymbol
                .registerTypeAdapter(WinningCombination.class, new WinningCombinationAdapter()) // Custom deserializer for WinningCombination
                .setPrettyPrinting() // Enables pretty printing of the JSON output
                .create();
    }

    /**
     * Loads the game configuration from a specified JSON file. If the provided configuration
     * file path is null or empty, it defaults to loading the configuration from the default file path.
     *
     * @param config the file path of the configuration JSON file, or null to use the default path
     * @return the deserialized GameConfiguration object
     * @throws ConfigurationException if there is an issue reading or parsing the file
     */
    public GameConfiguration loadConfiguration(String config) {
        String fileName = config != null && !config.isEmpty() ? config : DEFAULT_FILE_NAME;

        fileName = formatFileName(fileName);

        try (InputStreamReader reader = getReader(fileName)) {
            return gson.fromJson(reader, GameConfiguration.class);
        } catch (IOException e) {
            throw new ConfigurationException("Error loading configuration: " + e.getMessage(), e);
        }
    }

    private static String formatFileName(String fileName) {
        if(fileName.charAt(0) != '/') {
            fileName = "/" + fileName;
        }
        return fileName;
    }

    /**
     * Retrieves an InputStreamReader for the specified configuration file.
     * It attempts to locate the file in the resources folder. If the file is not found,
     * a FileNotFoundException is thrown.
     *
     * @param fileName the name of the configuration file
     * @return an InputStreamReader for the configuration file
     * @throws FileNotFoundException if the configuration file cannot be found
     */
    private InputStreamReader getReader(String fileName) throws FileNotFoundException {
        InputStream inputStream = AppConfiguration.class.getResourceAsStream(fileName);
        return new InputStreamReader(inputStream);
    }
}
