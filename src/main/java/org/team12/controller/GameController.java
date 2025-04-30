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
import org.team12.model.entities.Enemy;
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
    private Rectangle playerHitbox;

    public GameController(Map map, Player player) {
        this.map = map;
        this.player = player;
        this.isRunning = true;
        this.playerHitbox = new Rectangle();
    }

    public void update() {
        // Update player movement, logic
        //player.update();
        generatePlayerHitbox();
        checkPlayerPickup();
        checkEnemyAttack();
    }

    public void stopGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void generatePlayerHitbox() {
        playerHitbox = new Rectangle(
                player.worldX + player.hitbox.x,
                player.worldY + player.hitbox.y,
                player.hitbox.width,
                player.hitbox.height
        );

    }

    public void checkPlayerPickup() {

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

    public void checkEnemyAttack() {
        for (Enemy enemy : map.getEnemiesOnMap()) {
            // Define the enemy's hostility detection area as a square around its position
            Rectangle enemyHostilityBox = new Rectangle(
                    enemy.worldX - enemy.getHostilityArea() / 2,
                    enemy.worldY - enemy.getHostilityArea() / 2,
                    enemy.getHostilityArea(),
                    enemy.getHostilityArea()
            );

            if (playerHitbox.intersects(enemyHostilityBox)) {
                enemy.enemyAttack(player);
                // Reduce player's life (assuming you have a method or field for this)
                //player.reduceLives();
            }
        }
    }

}


