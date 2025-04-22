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
 * Class: GameController
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.team12.model.*;
import org.team12.model.entities.*;


public class GameController {
    private Map map;

    private Player player;
    private Enemy goon;
    private LilyFinalBoss lily;
    private Sword sword;
    private MagicDust magicDust;
    private RiddleChest riddleChest;

    private StateManager stateManager;

    public GameController() {
        map = new Map();
        player = new Player();
        lily = new LilyFinalBoss();
        sword = new Sword();
        magicDust = new MagicDust();
        riddleChest = new RiddleChest();
        stateManager = new StateManager();}

    public void initializeGame() {
        map.generateMap();
    }

    public void startGame() {
        map.placeObject(player);
    }

    public void proceedNextLevel() {

    }

    /**
     * Validate player's access from Lvl.1 to Lvl.2
     */
    public boolean validateLevel1() {
        // Check if player has all items and is in the correct position (door)
        if (player.hasAllItems) {
            return true;
        }
        return false;
    }

    public boolean validateLevel2() {
        if ()
    }

}
