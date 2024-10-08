package integrated.infraestructure.application.services;

import com.cyberspeed.config.AppConfiguration;
import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.domain.model.game.GameResult;
import com.cyberspeed.infraestructure.application.service.GameValidator;
import com.cyberspeed.utils.SymbolUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GameValidatorTest {

    private GameValidator gameValidator;
    private AppConfiguration appConfiguration;
    private GameConfiguration gameConfiguration;


    @BeforeEach
    public void init() {
        appConfiguration = new AppConfiguration();
        gameConfiguration = appConfiguration.loadConfiguration("config-test.json");
        gameValidator = new GameValidator(gameConfiguration, new SymbolUtils(gameConfiguration.symbols()));
    }

    @Test
    public void shouldValidateNoReward() {
        String[][] expectedMatrix = {
                {"A", "A", "C"},
                {"E", "B", "5x"},
                {"F", "B", "C"}
        };
        Map<String, List<String>> expectedCombinations = new HashMap<>();

        GameResult gameResult = gameValidator.validate(100, expectedMatrix);

        assertArrayEquals(gameResult.matrix(), expectedMatrix);
        assertNull(gameResult.appliedBonusSymbol());
        assertEquals(gameResult.appliedWinningCombinations(), expectedCombinations);
        assertEquals(gameResult.reward(), 0.00);
    }

    @Test
    public void shouldValidateWinningCombinationWithBonus() {
        String[][] expectedMatrix = {
                {"A", "A", "B"},
                {"A", "+1000", "B"},
                {"A", "A", "B"}
        };
        Map<String, List<String>> expectedCombinations = new HashMap<>();
        expectedCombinations.put("A", Arrays.asList("same_symbol_5_times", "same_symbols_vertically"));
        expectedCombinations.put("B", Arrays.asList("same_symbol_3_times", "same_symbols_vertically"));

        GameResult gameResult = gameValidator.validate(100, expectedMatrix);

        assertArrayEquals(gameResult.matrix(), expectedMatrix);
        assertEquals(gameResult.appliedBonusSymbol(), "+1000");
        assertEquals(gameResult.appliedWinningCombinations(), expectedCombinations);
        assertEquals(gameResult.reward(), 6600.00);
    }

    @Test
    public void shouldValidateWinningCombinationWithMultiplier() {
        String[][] expectedMatrix = {
                {"A", "B", "C"},
                {"E", "B", "10x"},
                {"F", "D", "B"}
        };
        Map<String, List<String>> expectedCombinations = new HashMap<>();
        expectedCombinations.put("B", List.of("same_symbol_3_times"));

        GameResult gameResult = gameValidator.validate(100, expectedMatrix);

        assertArrayEquals(gameResult.matrix(), expectedMatrix);
        assertEquals(gameResult.appliedBonusSymbol(), "10x");
        assertEquals(gameResult.appliedWinningCombinations(), expectedCombinations);
        assertEquals(gameResult.reward(), 3000.00);
    }

    @Test
    public void shouldValidateWinningCombinationWithSameSymbol3TimesAndSameSymbolsHorizontally() {
        String[][] expectedMatrix = {
                {"C", "C", "C"},
                {"A", "B", "5x"},
                {"F", "D", "B"}
        };
        Map<String, List<String>> expectedCombinations = new HashMap<>();
        expectedCombinations.put("C", Arrays.asList("same_symbol_3_times", "same_symbols_horizontally"));

        GameResult gameResult = gameValidator.validate(100, expectedMatrix);

        assertArrayEquals(gameResult.matrix(), expectedMatrix);
        assertEquals(gameResult.appliedBonusSymbol(), "5x");
        assertEquals(gameResult.appliedWinningCombinations(), expectedCombinations);
        assertEquals(gameResult.reward(), 2500.0);
    }

    @Test
    public void shouldValidateWinningCombinationWithSameSymbol3TimesAndSameSymbolsDiagonallyRightToLeft() {
        String[][] expectedMatrix = {
                {"A", "C", "B"},
                {"A", "B", "MISS"},
                {"B", "D", "D"}
        };
        Map<String, List<String>> expectedCombinations = new HashMap<>();
        expectedCombinations.put("B", Arrays.asList("same_symbol_3_times", "same_symbols_diagonally_right_to_left"));

        GameResult gameResult = gameValidator.validate(100, expectedMatrix);

        assertArrayEquals(gameResult.matrix(), expectedMatrix);
        assertNull(gameResult.appliedBonusSymbol());
        assertEquals(gameResult.appliedWinningCombinations(), expectedCombinations);
        assertEquals(gameResult.reward(), 1500.00);
    }

    @Test
    public void shouldValidateNoBonusWithMissedSymbolAndSameSymbol3TimesAndSameSymbolsDiagonallyLeftToRight() {
        String[][] expectedMatrix = {
                {"A", "D", "F"},
                {"B", "A", "MISS"},
                {"C", "D", "A"}
        };
        Map<String, List<String>> expectedCombinations = new HashMap<>();
        expectedCombinations.put("A", Arrays.asList("same_symbol_3_times", "same_symbols_diagonally_left_to_right"));

        GameResult gameResult = gameValidator.validate(100, expectedMatrix);

        assertArrayEquals(gameResult.matrix(), expectedMatrix);
        assertNull(gameResult.appliedBonusSymbol());
        assertEquals(gameResult.appliedWinningCombinations(), expectedCombinations);
        assertEquals(gameResult.reward(), 2500.00);
    }

    @Test
    public void shouldValidateWinningCombinationWithAllPossibleWinningCombinations() {
        String[][] expectedMatrix = {
                {"A", "A", "A"},
                {"A", "A", "A"},
                {"A", "A", "A"}
        };
        Map<String, List<String>> expectedCombinations = new HashMap<>();
        expectedCombinations.put("A", Arrays.
                asList("same_symbol_9_times", "same_symbols_horizontally", "same_symbols_vertically", "same_symbols_diagonally_left_to_right", "same_symbols_diagonally_right_to_left"));

        GameResult gameResult = gameValidator.validate(100, expectedMatrix);

        assertArrayEquals(gameResult.matrix(), expectedMatrix);
        assertNull(gameResult.appliedBonusSymbol());
        assertEquals(gameResult.appliedWinningCombinations(), expectedCombinations);
        assertEquals(gameResult.reward(), 1000000.00);
    }


}
