/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:33 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: Player
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.InputController;
import org.team12.view.GameUI;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

public class Player extends Entity {
    GameUI gp;
    InputController inputController;

    public Player(GameUI gp, InputController inputController) {
        this.gp = gp;
        this.inputController = inputController;
    }
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

}
