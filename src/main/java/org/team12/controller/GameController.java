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
import org.team12.model.entities.RiddleChest;
import org.team12.states.ItemState;
import org.team12.view.GameUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameController {
    private Map map;
    private Player player;
    private boolean isRunning;

    public GameController(Map map, Player player) {
        this.map = map;
        this.player = player;
        this.isRunning = true;
    }

    public void update() {
        // Update player movement, logic
        //player.update();
        checkPlayerPickup();
    }

    public void stopGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void checkPlayerPickup() {
        Rectangle playerHitbox = new Rectangle(
                player.worldX + player.hitbox.x,
                player.worldY + player.hitbox.y,
                player.hitbox.width,
                player.hitbox.height
        );

        // Only check against interactable items
        for (Item item : new ArrayList<>(map.getItemsOnMap())) { // avoid ConcurrentModification
            if (item.getItemState() != ItemState.INTERACTABLE) continue;

            Rectangle itemHitbox = new Rectangle(
                    item.getWorldX(), item.getWorldY(),
                    GameUI.getTileSize(), GameUI.getTileSize()
            );

            if (playerHitbox.intersects(itemHitbox)){
                boolean pickedUp = player.pickUpItem(item);
                if (pickedUp) {
                    map.removeItem(item);
                    break; // stop after first pickup
                }
            }
        }
    }

}
