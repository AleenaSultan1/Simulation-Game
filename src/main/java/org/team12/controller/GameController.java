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
import org.team12.model.entities.Item;
import org.team12.model.entities.Player;
import org.team12.view.GameUI;

import java.awt.*;
import java.util.Iterator;

public class GameController {
    private Map map;
    private Player player;
    private boolean isRunning;

    public GameController(Map map, Player player, GameUI gameUI) {
        this.map = map;
        this.player = player;
        this.isRunning = true;
    }

    public void update() {
        // Update player movement, logic
        player.update();

        // Update map (remove picked up items etc.)
        map.updateItemsOnMap();

        checkPlayerPickup();
    }

    public void stopGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void checkPlayerPickup() {
        Rectangle playerHitbox = new Rectangle(player.worldX, player.worldY, player.hitboxWidth, player.hitboxHeight);

        Iterator<Item> iterator = map.itemsOnMap.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            Rectangle itemHitbox = new Rectangle(item.getWorldX(), item.getWorldY(), GameUI.tileSize, GameUI.tileSize);

            if (playerHitbox.intersects(itemHitbox)) {
                // Player picked up the item!
                // Example: maybe you update player inventory here?
                //player.addToInventory(item);

                // Remove item from map
                iterator.remove();
            }
        }
    }
}
