/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:50 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: GameControllerTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.team12.controller.GameController;
import org.team12.controller.InputController;
import org.team12.model.Map;
import org.team12.states.GameState;
import org.team12.view.PlayerHud;

public class GameControllerTest {

    private GameController gameController;
    private InputController inputController;

    @BeforeEach
    public void setUp() {
        inputController = new InputController();
        Map map = new Map("/map/dungeonMap.txt", false);
        PlayerHud playerHud = new PlayerHud(32); // You may need to mock if constructor fails
        gameController = new GameController(map, inputController, playerHud);
    }

    @Test
    public void testInitialGameStateIsPause() {
        assertEquals(GameState.PAUSE, gameController.getGameState());
    }

    @Test
    public void testPlayerExistsAfterInit() {
        assertNotNull(gameController.getPlayer());
    }

    @Test
    public void testCheckInventoryWhenEmpty() {
        assertFalse(gameController.checkPlayerInventory());
    }
}

