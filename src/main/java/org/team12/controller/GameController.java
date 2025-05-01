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
import org.team12.states.EnemyStatus;
import org.team12.states.ItemState;
import org.team12.view.GameUI;

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
//        player.update();
        generateNewPlayerHitbox();
        checkEnemyHostility();
        if (inputController.interactionKeyPressed) {
            checkPlayerPickup();
        }
        if (inputController.attackKeyPressed) {
            System.out.println("Attack key pressed");
            checkPlayerAttack();
        }
    }
    public void generateNewPlayerHitbox() {
        playerHitbox = new Rectangle(
                player.worldX + player.getHitbox().x,
                player.worldY + player.getHitbox().y,
                player.getHitbox().width,
                player.getHitbox().height);
    }


    public void stopGame() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }


    public void generatePlayerHitbox() {
        playerHitbox = new Rectangle(
                player.worldX + player.getHitbox().x,
                player.worldY + player.getHitbox().y,
                player.getHitbox().width,
                player.getHitbox().height);
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
    public void checkPlayerAttack() {

        // Only check against alive Enemies
        for (Enemy enemy : new ArrayList<>(map.getEnemiesOnMap())) { // avoid ConcurrentModification
            if (enemy.getState() == EnemyStatus.DEAD) continue;

            int attackSize = player.getAttackRangeScale();


            Rectangle enemyHitBox = new Rectangle(
                    enemy.worldX + enemy.getHitbox().x,
                    enemy.worldY + enemy.getHitbox().y,
                    enemy.getHitbox().width,
                    enemy.getHitbox().height);

            if (player.getAttackRange().intersects(enemyHitBox)){
                System.out.println("Try attacking");
                boolean atattacked = player.attackEnemy(enemy);
                if (atattacked) {
                    break; // stop after first enemy
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

            // Define the enemy's attack detection area as a square around its position
            Rectangle enemyAttackRange = new Rectangle(
                    enemy.worldX - enemy.getAttackRange() / 2,
                    enemy.worldY - enemy.getAttackRange() / 2,
                    enemy.getAttackRange(),
                    enemy.getAttackRange()
            );


            if (playerHitbox.intersects(enemyHostilityBox)) {
                enemy.enemyMoveToPlayer(player);
                // Reduce player's life (assuming you have a method or field for this)
                //player.reduceLives();

                if (playerHitbox.intersects(enemyAttackRange)) {
                    enemy.enemyAttackPlayer(player);
                }
            } else {
                enemy.moveRandomly();
            }
        }
    }

}


