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

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean interactionKeyPressed, attackKeyPressed, enterKeyPressed, escKeyPressed;

    public boolean upJustPressed, downJustPressed, leftJustPressed, rightJustPressed;
    public boolean attackKeyJustPressed, interactionKeyJustPressed, enterKeyJustPressed, escKeyJustPressed;


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
            upJustPressed = true;
        }
        if (code == KeyEvent.VK_S|| code == KeyEvent.VK_DOWN) {
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
