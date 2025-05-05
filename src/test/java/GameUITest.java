/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 1:08 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: GameUITest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.view.GameUI;

import static org.junit.jupiter.api.Assertions.*;

public class GameUITest {

    private GameUI gameUI;

    @BeforeEach
    public void setUp() {
        gameUI = new GameUI();
    }

    @Test
    public void testTileSizeIsCorrect() {
        assertEquals(48, GameUI.getTileSize()); // 16 * 3 = 48
    }

    @Test
    public void testScreenDimensions() {
        assertEquals(768, GameUI.getScreenWidth()); // 48 * 16 = 768
        assertEquals(576, GameUI.getScreenHeight()); // = 48 * 12 = 576
    }

    @Test
    public void testGameUIIsFocusable() {
        assertTrue(gameUI.isFocusable());
    }

    @Test
    public void testGameControllerExists() {
        assertNotNull(gameUI);
        assertNotNull(gameUI.getComponents());
    }
}

