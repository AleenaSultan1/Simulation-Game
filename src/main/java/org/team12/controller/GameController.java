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

import org.team12.model.Map;
import org.team12.model.entities.*;
import org.team12.view.GameUI;
import java.awt.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameController {
    private Player player;
    private Map map;
    private boolean isRunning;
    private LilyFinalBoss lily;
    private Sword sword;
    private MagicDust magicDust;
    private RiddleChest riddleChest;
    private final List<Item> items;

    public GameController(Map map, Player player) {
        items = map.getItemsOnMap();
        this.map = map;
        this.player = player;
        this.isRunning = true;
    }

    public void update() {
        // Update player movement, logic
        //player.update();
        player.update();
        checkPlayerPickup();
    }

    public void stopGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void checkPlayerPickup() {
        Rectangle playerHitbox = new Rectangle(player.worldX, player.worldY, player.getHitboxWidth(), player.getHitboxHeight());

        for (Item item : map.getItemsOnMap()) {
            Rectangle itemHitbox = new Rectangle(item.getWorldX(), item.getWorldY(), GameUI.getTileSize(), GameUI.getTileSize());

            Iterator<Item> iterator = map.getItemsOnMap().iterator();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                Rectangle itemHitbox = new Rectangle(item.getWorldX(), item.getWorldY(), GameUI.getTileSize(), GameUI.getTileSize());

                if (playerHitbox.intersects(itemHitbox)) {
                    //Player picked up the item!
                    item.pickUp();
                    player.pickUpItem(item);
                    //player.addToInventory(item);
                    player.getInventory().add(item);
                    // Remove item from map

                    map.removeItemsOnMap();

                }
                iterator.remove();

            }
        }
    }
}
