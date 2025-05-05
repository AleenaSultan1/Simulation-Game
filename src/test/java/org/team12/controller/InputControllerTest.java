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
 * Class: InputControllerTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class InputControllerTest {

    private InputController input;

    @BeforeEach
    void setUp() {
        input = new InputController();
    }

    @Test
    void testUpKeyPressed() {
        input.keyPressed(new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_W, 'W'));
        assertTrue(input.upPressed);
        assertTrue(input.upJustPressed);
    }

    @Test
    void testInteractionKey() {
        input.keyPressed(new KeyEvent(new java.awt.Label(), KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_SPACE, ' '));
        assertTrue(input.interactionKeyPressed);
        assertTrue(input.interactionKeyJustPressed);
    }

    @Test
    void testKeyReleased() {
        input.upPressed = true;
        input.keyReleased(new KeyEvent(new java.awt.Label(), KeyEvent.KEY_RELEASED, 0, 0, KeyEvent.VK_W, 'W'));
        assertFalse(input.upPressed);
    }

    @Test
    void testResetJustPressed() {
        input.upJustPressed = true;
        input.escKeyJustPressed = true;
        input.resetJustPressed();
        assertFalse(input.upJustPressed);
        assertFalse(input.escKeyJustPressed);
    }
}
