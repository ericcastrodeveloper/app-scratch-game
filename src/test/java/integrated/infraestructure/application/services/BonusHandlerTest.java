package integrated.infraestructure.application.services;

import com.cyberspeed.config.AppConfiguration;
import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.infraestructure.application.service.BonusHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusHandlerTest {
    private BonusHandler bonusHandler;


    @BeforeEach
    public void init() {
        AppConfiguration appConfiguration = new AppConfiguration();
        GameConfiguration gameConfiguration = appConfiguration.loadConfiguration("config.json");
        bonusHandler = new BonusHandler(gameConfiguration);
    }

    @Test
    public void test1() {
        double reward = bonusHandler.applyBonus(100.00, "10x");

        assertEquals(reward, 1000.00);
    }

    @Test
    public void test2() {
        double reward = bonusHandler.applyBonus(100.00, "5x");

        assertEquals(reward, 500.00);
    }

    @Test
    public void test3() {
        double reward = bonusHandler.applyBonus(100.00, "+1000");

        assertEquals(reward, 1100.00);
    }

    @Test
    public void test4() {
        double reward = bonusHandler.applyBonus(100.00, "+500");

        assertEquals(reward, 600.00);
    }

    @Test
    public void test5() {
        double reward = bonusHandler.applyBonus(100.00, "MISS");

        assertEquals(reward, 100.00);
    }
}
