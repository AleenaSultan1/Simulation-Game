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

import javafx.util.Pair;
import org.team12.model.*;
import org.team12.model.entities.*;

import java.util.Random;


public class GameController {
    private Map map;

    private Player player;
    private LilyFinalBoss lily;
    private Sword sword;
    private MagicDust magicDust;
    private RiddleChest riddleChest;
    private StateManager stateManager;

    private int numGoons = 5;
    private int lvlHeight = 20;

    private Enemy[] goons = new Enemy[numGoons];

    private Pair<Integer, Integer> lvlOneDim = new Pair<>(lvlHeight, 0);
    private Pair<Integer, Integer> lvlTwoDim = new Pair<>(lvlHeight*2 + 1, lvlHeight + 1);
    private Pair<Integer, Integer> lvlThreeDim = new Pair<>(lvlHeight*3 + 2, lvlHeight*2 + 2);


    public GameController() {
        lily = new LilyFinalBoss();
        sword = new Sword();
        magicDust = new MagicDust();
        riddleChest = new RiddleChest();
        stateManager = new StateManager();
    }

    public void initializeGame() {
        player = new Player();

        // Make map with hardcoded dimensions
        map = new Map(lvlHeight + 4, lvlHeight + 2);

        // Generate 5 random Goons in Lvl2
        for (int i = 0; i < numGoons; i++) {
            goons[i] = new Enemy();

            int possibleX = lvlTwoDim.getKey() - lvlTwoDim.getValue();
            int possibleY = lvlHeight;

            Random rand = new Random();
            int x = rand.nextInt(lvlHeight) + lvlTwoDim.getValue();
            int y = rand.nextInt(lvlHeight) + 1;
            map.placeEnemy(goons[i], x, y);
        }

        // Place Lily
        map.placeEnemy(lily, 50, 10);

        // Place items
        map.placeItem(magicDust, 10, 10);
        map.placeItem(riddleChest, 15, 15);
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
