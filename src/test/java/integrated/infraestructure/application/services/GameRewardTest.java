package integrated.infraestructure.application.services;

import com.cyberspeed.config.AppConfiguration;
import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.infraestructure.application.service.GameReward;
import com.cyberspeed.utils.SymbolUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRewardTest {
    private GameReward gameReward;


    @BeforeEach
    public void init() {
        AppConfiguration appConfiguration = new AppConfiguration();
        GameConfiguration gameConfiguration = appConfiguration.loadConfiguration("config-test.json");
        SymbolUtils symbolUtils = new SymbolUtils(gameConfiguration.symbols());
        gameReward = new GameReward(gameConfiguration, symbolUtils);
    }

    @Test
    public void testCalculatePrizeWithMultipleWinningCombinationsAndBonusShouldReturnExpectedReward() {
        Map<String, List<String>> winningCombinations = new HashMap<>();
        winningCombinations.put("A", Arrays.asList("same_symbol_5_times", "same_symbols_vertically"));
        winningCombinations.put("B", Arrays.asList("same_symbol_3_times", "same_symbols_vertically"));
        double reward = gameReward.calculatePrize(100, winningCombinations, "+1000");
        assertEquals(reward, 6600.0);
    }

    @Test
    public void testCalculatePrizeWithSingleWinningCombinationAndBonusshouldReturnExpectedReward() {
        Map<String, List<String>> winningCombinations = new HashMap<>();
        winningCombinations.put("B", List.of("same_symbol_3_times"));
       double reward =  gameReward.calculatePrize(100, winningCombinations, "10x");
        assertEquals(reward, 3000.0);

    }

    @Test
    public void testCalculatePrizeWithNoWinningCombinationsShouldReturnZero() {
        Map<String, List<String>> winning = new HashMap<>();
        double reward =  gameReward.calculatePrize(100, winning, null);
        assertEquals(reward, 0.0);
    }

    @Test
    public void testCalculatePrizeWithAllPossibleWinningCombinationAndShouldReturnExpectedReward() {
        Map<String, List<String>> winningCombinations = new HashMap<>();
        winningCombinations.put("A", Arrays.
                asList("same_symbol_9_times", "same_symbols_horizontally", "same_symbols_vertically", "same_symbols_diagonally_left_to_right", "same_symbols_diagonally_right_to_left"));

        double reward =  gameReward.calculatePrize(100, winningCombinations, null);
        assertEquals(reward, 1000000.00);

    }

}
