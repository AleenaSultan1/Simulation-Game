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
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.team12.states.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, interactionKeyPressed, attackKeyPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // PLAY STATE
        if (GameController.getGameState() != GameState.END ) {

            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }

            if (code == KeyEvent.VK_SPACE) {
                interactionKeyPressed = true;
            }
            if (code == KeyEvent.VK_E) {
                attackKeyPressed = true;
            }

            if (code == KeyEvent.VK_P) {
                GameController.setGameState(GameState.PAUSE);
            }
        } else if (GameController.getGameState() == GameState.PAUSE) {
            // PAUSE BUTTON
            if (code == KeyEvent.VK_P) {
                GameController.setGameState(GameState.START);
            }
        }

    }

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


    }
}
