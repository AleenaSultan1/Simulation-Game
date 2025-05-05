/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:49 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: GameControllerTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Map;
import org.team12.model.items.MagicDust;
import org.team12.model.items.Sword;
import org.team12.states.GameState;
import org.team12.view.GameUI;
import org.team12.view.PlayerHud;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {

    private GameController gameController;
    private Map map;
    private InputController inputController;
    private PlayerHud playerHud;

    @BeforeEach
    void setUp() {
        map = new Map("/map/dungeonMap.txt", false);
        inputController = new InputController();
        playerHud = new PlayerHud(GameUI.getTileSize());
        gameController = new GameController(map, inputController, playerHud);
    }

    @Test
    void testInitialGameStateIsPause() {
        assertEquals(GameState.PAUSE, gameController.getGameState());
    }

    @Test
    void testUpdateMapLevelDoesNotCrash() {
        assertDoesNotThrow(() -> gameController.updateMapLevel());
    }

    @Test
    void testCheckPlayerInventoryWithNoItems() {
        assertFalse(gameController.checkPlayerInventory());
    }

    @Test
    void testCheckPlayerInventoryWithItems() {
        var player = gameController.getPlayer();
        player.pickUpItem(new MagicDust());
        player.pickUpItem(new Sword());
        assertTrue(gameController.checkPlayerInventory());
    }

    @Test
    void testHandleLaptopInputDoesNotCrashWithNull() {
        assertDoesNotThrow(() -> gameController.handleLaptopInput(inputController, null));
    }

    @Test
    void testGetPlayerIsNotNull() {
        assertNotNull(gameController.getPlayer());
    }
}
