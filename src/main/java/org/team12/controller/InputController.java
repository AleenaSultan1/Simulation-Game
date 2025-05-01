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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputController implements KeyListener {

    GameController gameController;
    boolean checkDrawTime;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    // Constructor
    public InputController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // TITLE STATE
        if (gameController.gameState == gameController.titleState){
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gameController.pHud.commandNumber --;
                if (gameController.pHud.commandNumber<0){
                    gameController.pHud.commandNumber = 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gameController.pHud.commandNumber++;
                if (gameController.pHud.commandNumber>1) {
                    gameController.pHud.commandNumber = 0;
                }
            }

            if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
                // If user selects play, play the game
                if (gameController.pHud.commandNumber == 0){
                    gameController.gameState = gameController.playState;
                }
                // otherwise quit
                if (gameController.pHud.commandNumber == 1){
                    System.exit(0);
                }
            }
        }


        // PLAY STATE
        if (gameController.gameState == gameController.playState) {
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
            if (code == KeyEvent.VK_P) {
                gameController.gameState = gameController.pauseState;
            }
        } else if (gameController.gameState == gameController.pauseState) {
            // PAUSE BUTTON
            if (code == KeyEvent.VK_P) {
                gameController.gameState = gameController.playState;
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
    }
}
