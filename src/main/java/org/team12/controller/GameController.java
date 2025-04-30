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
import org.team12.states.EnemyStatus;
import org.team12.view.GameUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameController {
    private Player player;
    private LilyFinalBoss lily;
    private Sword sword;
    private MagicDust magicDust;
    private RiddleChest riddleChest;
    private final List<Item> items;


    public GameController(Map map) {
        items = map.getItemsOnMap();
    }

    /**
     * Validate player's access from Lvl.1 to Lvl.2
     */
    public boolean validateLevel1() {
        // Check if player has all items and is in the correct position (door)
//        return player.hasAllItems();
        return false;
    }

    public boolean validateLevel2() {
        for (Enemy goon : goons) {
            if (goon.getState() != EnemyStatus.DEAD) {
                return false;
            }
        }
        return true;
    }

//    public boolean interactWithChest(RiddleChest riddleChest) {
//        player.interact(riddleChest); // This should not update the chest's status
//        if (riddleChest.checkUserInput())
//    }



}
