package integrated.infraestructure.application.services;

import com.cyberspeed.config.AppConfiguration;
import com.cyberspeed.config.GameConfiguration;
import com.cyberspeed.domain.service.ProbabilityAggregator;
import com.cyberspeed.domain.service.ProbabilityBonusService;
import com.cyberspeed.domain.service.ProbabilityStandardService;
import com.cyberspeed.domain.service.SelectorSymbol;
import com.cyberspeed.infraestructure.application.service.MatrixGenerator;
import com.cyberspeed.utils.RandomGenerator;
import com.cyberspeed.utils.SymbolUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MatrixGeneratorTest {
    private MatrixGenerator matrixGenerator;
    private AppConfiguration appConfiguration;
    private GameConfiguration gameConfiguration;
    private ProbabilityBonusService probabilityBonusService;
    private ProbabilityStandardService probabilityStandardService;
    private ProbabilityAggregator probabilityAggregator;
    private SelectorSymbol selectorSymbol;

    @BeforeEach
    public void init() {
        appConfiguration = new AppConfiguration();
        gameConfiguration = appConfiguration.loadConfiguration("config-test-matrix.json");
        probabilityBonusService = new ProbabilityBonusService(gameConfiguration.probabilities().bonusSymbolProbabilities());
        probabilityStandardService = new ProbabilityStandardService(gameConfiguration.probabilities().standardSymbolProbabilities());
        probabilityAggregator = new ProbabilityAggregator(probabilityStandardService, probabilityBonusService);
        selectorSymbol = new SelectorSymbol(probabilityAggregator, new RandomGenerator());
        matrixGenerator = new MatrixGenerator(selectorSymbol, new SymbolUtils(gameConfiguration.symbols()));
    }

    @Test
    public void shouldGenerateMatrixWithExpectedSymbols() {
        String[][] expectedMatrix = {
                {"A", "D", "A"},
                {"B", "E", "B"},
                {"C", "F", "C"}
        };

        String[][] actualMatrix  = matrixGenerator.generateMatrix(3, 3);
        assertArrayEquals(expectedMatrix, actualMatrix);
    }

    @Test
    public void shouldGenerate4x4MatrixWithProbabilitiesOfColumnAndRowZero() {
        String[][] expectedMatrix = {
                {"A", "D", "A", "A"},
                {"B", "E", "B", "A"},
                {"C", "F", "C", "A"},
                {"A", "A", "A", "A"}
        };

        String[][] actualMatrix  = matrixGenerator.generateMatrix(4, 4);
        assertArrayEquals(expectedMatrix, actualMatrix);
    }
}
