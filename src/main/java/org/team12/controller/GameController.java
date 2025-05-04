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

import org.team12.model.Items.Item;
import org.team12.model.Items.Laptop;
import org.team12.model.Map;
import org.team12.model.entities.*;
import org.team12.states.EnemyStatus;
import org.team12.states.GameState;
import org.team12.states.ItemState;
import org.team12.view.GameUI;
import org.team12.view.PlayerHud;

import java.awt.*;
import java.util.ArrayList;

public class GameController {
    private Map map;
    private Player player;
    private Rectangle playerHitbox;
    private CollisionController collisionController;
    private InputController inputController;
    private GameState gameState;
    private PlayerHud playerHud;
    private Laptop currLaptop;
    private boolean level2;
    private boolean magicChestOpen;


    public GameController(Map map, InputController inputController, PlayerHud playerHud) {
        this.map = map;
        this.inputController = inputController;
        this.playerHud = playerHud;

        collisionController = new CollisionController(map);
        map.setCollisionController(collisionController);
        player = new Player(inputController, collisionController, 20);
        map.setPlayer(player);

        this.playerHitbox = player.getHitbox();
        this.level2 = false;
        //Game State
        gameState = GameState.PAUSE;

    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getPlayer() {
        return player;
    }

    public void updateMapLevel() {
        //setGameState(GameState.LEVEL_2);
        map.loadMap("/map/dungeonMap.txt", true);
        map.setCollisionController(collisionController); // RE-ASSIGN after reloading
        map.setPlayer(player); // RE-ASSIGN player as well
        System.out.println("Map level updated to LEVEL_2");
//        collisionController.setMap(map);
    }

    public void update() {
        switch (gameState) {
            case PAUSE:
                GameState selectedState = handleTitleScreenInput(inputController);
                if (selectedState == GameState.END) {
                    gameState = GameState.END;
                } else if (selectedState == GameState.PLAYING) {
                    gameState = GameState.PLAYING;
                }
                break;
            case PLAYING:
                // Update player movement, logic per InputController
                player.update();
                generateNewPlayerHitbox();
                checkEnemyHostility();
                // Check for level transition
                if (map.getEnemiesOnMap().isEmpty()) {
                    level2 = true;
                    updateMapLevel();
                    // Reset player position for new level
                    player.worldX = 100;
                    player.worldY = 100;
                }
                if (inputController.interactionKeyJustPressed) {
                    checkPlayerPickup();
                    inputController.resetJustPressed();
                }
                if (inputController.attackKeyJustPressed) {
                    System.out.println("Attack key pressed");
                    checkPlayerAttack();
                    inputController.resetJustPressed();
                }
                if (inputController.escKeyJustPressed) {
                    gameState = GameState.PAUSE;
                }
                break;
            case QUIZ:
                handleLaptopInput(inputController, currLaptop);
                break;
            case END:
                System.out.println("End");
                System.exit(0);

        }

    }
    public void generateNewPlayerHitbox() {
        playerHitbox = new Rectangle(
                player.worldX + player.getHitbox().x,
                player.worldY + player.getHitbox().y,
                player.getHitbox().width,
                player.getHitbox().height);
    }

    public Rectangle generateNewEnemyHitbox(Enemy enemy) {
        return new Rectangle(
                enemy.worldX + enemy.getHitbox().x,
                enemy.worldY + enemy.getHitbox().y,
                enemy.getHitbox().width,
                enemy.getHitbox().height);
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
                    if (item instanceof Laptop) {
                        playerHud.setLaptop((Laptop) item);
                        currLaptop = (Laptop) item;
                        if (currLaptop.isActive()) {
                            System.out.println("Laptop is active");
                            currLaptop.resetLaptop(); // Reset quiz progress
//                        ((Laptop)item).activate();
                            gameState = GameState.QUIZ;
                        }
                    }
                    else map.removeItem(item);
                    break; // stop after first pickup
                }
            }
        }
    }
    public void checkPlayerAttack() {

        // Only check against alive Enemies
        for (Enemy enemy : new ArrayList<>(map.getEnemiesOnMap())) { // avoid ConcurrentModification
            if (enemy.getState() == EnemyStatus.DEAD) continue;

            Rectangle enemyHitBox = new Rectangle(
                    enemy.worldX + enemy.getHitbox().x,
                    enemy.worldY + enemy.getHitbox().y,
                    enemy.getHitbox().width,
                    enemy.getHitbox().height);

            if (player.getAttackRange().intersects(enemyHitBox)){
                System.out.println("Try attacking");
                boolean attacked = player.attackEnemy(enemy);
                if (enemy.getState() == EnemyStatus.DEAD || enemy.getState() == EnemyStatus.CURED) {
                    if (enemy instanceof LilyFinalBoss && magicChestOpen) {
                        enemy.setEnemyState(EnemyStatus.CURED);
                    }
                    map.removeEnemy(enemy);
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

    public void handleLaptopInput(InputController input, Laptop laptop) {
        if (laptop == null || !laptop.isActive()) return;

        if (input.upJustPressed) {
            laptop.moveSelectionUp();
        }
        else if (input.downJustPressed) {
            laptop.moveSelectionDown();
        }
        else if (input.enterKeyJustPressed) {
            boolean correct = laptop.submitAnswer();
            if (!correct) {
                // Wrong answer - exit immediately
                laptop.resetLaptop();
                gameState = GameState.PLAYING;
            }
            else if (laptop.getItemState() == ItemState.UNINTERACTABLE) {
                // All questions answered correctly
                playerHud.showMessage("Quiz complete! You earned an A+!");
                laptop.deactivate();
                System.out.println("Quiz complete! You earned an A+!");
                gameState = GameState.PLAYING;
                input.resetJustPressed();
                return;
            }
            // If correct but not complete, stays in quiz for next question
            laptop.resetAnswerSubmitted();
        }
        else if (input.escKeyJustPressed) {
            laptop.resetLaptop();
            gameState = GameState.PLAYING;
        }
        input.resetJustPressed();
    }

    public GameState handleTitleScreenInput(InputController input) {
        if (inputController.downJustPressed | inputController.upJustPressed) {
            // Toggle
            playerHud.toggleTitleScreen();
            inputController.resetJustPressed();
        }
        if (inputController.enterKeyJustPressed & (playerHud.getCommandNumber() == -1)) {
            inputController.resetJustPressed();
            return GameState.PLAYING;
        } else if (inputController.enterKeyJustPressed & (playerHud.getCommandNumber() == 1)) {
            inputController.resetJustPressed();
            return GameState.END;
        }
        return null;
    }

}


