/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:53 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: InputControllerTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.controller.InputController;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class InputControllerTest {

    private InputController input;

    @BeforeEach
    public void setUp() {
        input = new InputController();
    }

    @Test
    public void testKeyPressedAndReleased() {
        input.keyPressed(new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'));
        assertTrue(input.upPressed);
        assertTrue(input.upJustPressed);

        input.keyReleased(new KeyEvent(new java.awt.Label(), KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W'));
        assertFalse(input.upPressed);
    }

    @Test
    public void testResetJustPressed() {
        input.keyPressed(new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_E, 'E'));
        assertTrue(input.attackKeyJustPressed);

        input.resetJustPressed();
        assertFalse(input.attackKeyJustPressed);
    }
}

