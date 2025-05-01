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
import org.team12.states.ItemState;

import java.awt.*;
import java.util.ArrayList;

public class GameController {
    private Map map;
    private Player player;
    private boolean isRunning;
    private Rectangle playerHitbox;
    private CollisionController collisionController;
    private InputController inputController;


    public GameController(Map map, InputController inputController) {
        this.map = map;

        collisionController = new CollisionController(map);
        map.setCollisionController(collisionController);
        this.inputController = inputController;
        player = new Player(inputController, collisionController, 20);
        map.setPlayer(player);

        this.isRunning = true;
        this.playerHitbox = player.getHitbox();
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        // Update player movement, logic per InputController
        player.update();
        checkEnemyHostility();
        if (inputController.interactionKeyPressed) {
            checkPlayerPickup();
        }
        if (inputController.attackKeyPressed) {
            // Player attack
        }
    }

    public void stopGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void checkPlayerPickup() {

        // Only check against interactable items
        for (Item item : new ArrayList<>(map.getItemsOnMap())) { // avoid ConcurrentModification
            if (item.getItemState() != ItemState.INTERACTABLE) continue;

            Rectangle itemHitbox = item.getHitbox();

            if (playerHitbox.intersects(itemHitbox)){
                boolean pickedUp = player.pickUpItem(item);
                if (pickedUp) {
                    map.removeItem(item);
                    break; // stop after first pickup
                }
            }
        }
    }

    public void checkEnemyHostility() {
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
            } else {
                enemy.moveRandomly();
            }
        }
    }

}


