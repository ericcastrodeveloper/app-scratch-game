package com.cyberspeed.infraestructure.application.validator;

import com.cyberspeed.infraestructure.application.exceptions.InvalidParametersException;

public class ParameterValidator {
    public static void validate(String[] args) {
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
}