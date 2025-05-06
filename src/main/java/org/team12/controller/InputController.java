/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:30 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: InputController
 *
 * Description: Handles keyboard input for player movement and actions.
 * Tracks both continuous key holds and one-time key presses for precise interaction control.
 * ****************************************
 */

package org.team12.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * InputController tracks player input using key states.
 * It supports both held and single-time (just pressed) key tracking for game actions.
 */
public class InputController implements KeyListener {

    // Continuous key press states
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean interactionKeyPressed, attackKeyPressed, enterKeyPressed, escKeyPressed;

    // One-time key press triggers (reset after use)
    public boolean upJustPressed, downJustPressed, leftJustPressed, rightJustPressed;
    public boolean attackKeyJustPressed, interactionKeyJustPressed, enterKeyJustPressed, escKeyJustPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    /**
     * Called when a key is pressed. Updates both held and justPressed flags.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
            upJustPressed = true;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
            downJustPressed = true;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
            leftJustPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            rightJustPressed = true;
        }
        if (code == KeyEvent.VK_SPACE) {
            interactionKeyPressed = true;
            interactionKeyJustPressed = true;
        }
        if (code == KeyEvent.VK_E) {
            attackKeyPressed = true;
            attackKeyJustPressed = true;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterKeyPressed = true;
            enterKeyJustPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escKeyPressed = true;
            escKeyJustPressed = true;
        }
    }

    /**
     * Called when a key is released. Resets held states.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SPACE) {
            interactionKeyPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            attackKeyPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterKeyPressed = false;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            escKeyPressed = false;
        }
    }

    /**
     * Resets all justPressed flags. Should be called at the end of input processing each frame.
     */
    public void resetJustPressed() {
        upJustPressed = false;
        downJustPressed = false;
        leftJustPressed = false;
        rightJustPressed = false;
        attackKeyJustPressed = false;
        interactionKeyJustPressed = false;
        enterKeyJustPressed = false;
        escKeyJustPressed = false;
    }
}
