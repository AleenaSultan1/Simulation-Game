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

import org.team12.model.items.Item;
import org.team12.model.items.Laptop;
import org.team12.model.items.MagicDust;
import org.team12.model.items.Sword;
import org.team12.model.Map;
import org.team12.model.Tile;
import org.team12.model.entities.*;
import org.team12.states.EnemyStatus;
import org.team12.states.GameState;
import org.team12.states.ItemState;
import org.team12.view.GameUI;
import org.team12.view.PlayerHud;

import java.awt.*;
import java.util.ArrayList;

/**
 * The central controller class that manages all game logic and state transitions.
 * Handles player input, enemy AI, item interactions, and level progression.
 */
public class GameController {
    // Core game components
    private Map map;
    private Player player;
    private Rectangle playerHitbox;
    private CollisionController collisionController;
    private InputController inputController;
    private GameState gameState;
    private PlayerHud playerHud;
    private Laptop currLaptop;

    // Level progression tracking
    private boolean level2;
    private LilyFinalBoss lily;

    /**
     * Constructs a new GameController with the specified dependencies.
     */
    public GameController(Map map, InputController inputController, PlayerHud playerHud) {
        this.map = map;
        this.inputController = inputController;
        this.playerHud = playerHud;

        // Setup systems
        collisionController = new CollisionController(map);
        map.setCollisionController(collisionController);

        // Setup player
        player = new Player(inputController, collisionController, 20);
        map.setPlayer(player);

        this.playerHitbox = player.getHitbox();
        this.level2 = false;
        gameState = GameState.PAUSE;
    }

    /** Returns the current game state. */
    public GameState getGameState() { return gameState; }

    /** Returns the current player instance. */
    public Player getPlayer() { return player; }

    /**
     * Load the second level map and update player reference.
     */
    public void updateMapLevel() {
        map.loadMap("/map/dungeonMap.txt", true);
        map.setCollisionController(collisionController);
        map.setPlayer(player);
        System.out.println("Map level updated to LEVEL_2");
    }

    /**
     * Main game loop logic based on the current GameState.
     */
    public void update() {
        switch (gameState) {
            case PLAYER_DEAD:
            case LILY_CURED:
            case PAUSE:
                GameState selectedState = handleTitleScreenInput(inputController);
                if (selectedState == GameState.END) {
                    gameState = GameState.END;
                } else if (selectedState == GameState.PLAYING) {
                    gameState = GameState.PLAYING;
                }
                break;
            case PLAYING:
                updatePlayingState();
                break;
            case QUIZ:
                handleLaptopInput(inputController, currLaptop);
                break;
            case END:
                System.exit(0);
        }
    }

    /**
     * Game logic while actively playing: input, AI, items, level switching.
     */
    private void updatePlayingState() {
        player.update();
        generateNewPlayerHitbox();
        checkEnemyHostility();

        // Level progression logic
        if (map.getEnemiesOnMap().isEmpty() && checkPlayerInventory() && !level2) {
            level2 = true;
            updateMapLevel();
            player.worldX = 100;
            player.worldY = 100;
        }

        if (player.getHP() == 0) gameState = GameState.PLAYER_DEAD;

        if (level2) {
            lily = getLily();
            if (lily.getHP() == 0) gameState = GameState.LILY_CURED;
        }

        // Handle input-based events
        if (inputController.interactionKeyJustPressed) {
            checkPlayerPickup();
            inputController.resetJustPressed();
        }
        if (inputController.attackKeyJustPressed) {
            checkPlayerAttack();
            inputController.resetJustPressed();
        }
        if (inputController.escKeyJustPressed) {
            gameState = GameState.PAUSE;
        }
    }

    /**
     * Returns true if the player has both MagicDust and Sword in inventory.
     */
    public boolean checkPlayerInventory() {
        boolean hasDust = false;
        boolean hasSword = false;
        for (Item item : player.getInventory()) {
            if (item instanceof MagicDust) hasDust = true;
            if (item instanceof Sword) hasSword = true;
        }
        return hasDust && hasSword;
    }

    /** Refresh player hitbox to match their current position. */
    public void generateNewPlayerHitbox() {
        playerHitbox = new Rectangle(
                player.worldX + player.getHitbox().x,
                player.worldY + player.getHitbox().y,
                player.getHitbox().width,
                player.getHitbox().height);
    }

    /** Handle item pickup interaction logic and edge cases. */
    public void checkPlayerPickup() {
        for (Item item : new ArrayList<>(map.getItemsOnMap())) {
            if (item.getItemState() != ItemState.INTERACTABLE) continue;

            int itemTileX = item.getWorldX() / GameUI.getTileSize();
            int itemTileY = item.getWorldY() / GameUI.getTileSize();
            Tile itemTile = map.getTile(itemTileX, itemTileY);

            if (itemTile == null || itemTile.getItem() != item) {
                System.out.println("Orphaned item detected at " + itemTileX + "," + itemTileY);
                continue;
            }

            Rectangle itemHitbox = new Rectangle(
                    item.getWorldX(), item.getWorldY(),
                    GameUI.getTileSize(), GameUI.getTileSize()
            );

            if (playerHitbox.intersects(itemHitbox)){
                boolean pickedUp = player.pickUpItem(item);
                if (pickedUp) {
                    if (item instanceof Laptop) {
                        handleLaptopPickup((Laptop)item);
                    } else {
                        map.removeItem(item);
                    }
                    break;
                }
            }
        }
    }

    /**
     * Begin quiz interaction with laptop item.
     */
    private void handleLaptopPickup(Laptop laptop) {
        playerHud.setLaptop(laptop);
        currLaptop = laptop;
        if (currLaptop.isActive()) {
            currLaptop.resetLaptop();
            gameState = GameState.QUIZ;
        }
    }

    /**
     * Returns Lily boss instance if present on map.
     */
    public LilyFinalBoss getLily() {
        for (Enemy enemy : map.getEnemiesOnMap()) {
            if (enemy instanceof LilyFinalBoss) {
                return (LilyFinalBoss) enemy;
            }
        }
        return null;
    }

    /**
     * Checks if player's attack intersects any enemy.
     * Removes enemy if killed.
     */
    public void checkPlayerAttack() {
        for (Enemy enemy : new ArrayList<>(map.getEnemiesOnMap())) {
            if (enemy.getState() == EnemyStatus.DEAD) continue;

            Rectangle enemyHitBox = new Rectangle(
                    enemy.worldX + enemy.getHitbox().x,
                    enemy.worldY + enemy.getHitbox().y,
                    enemy.getHitbox().width,
                    enemy.getHitbox().height);

            if (player.getAttackRange().intersects(enemyHitBox)){
                boolean attacked = player.attackEnemy(enemy);
                if (enemy.getState() == EnemyStatus.DEAD && attacked) {
                    map.removeEnemy(enemy);
                }
            }
        }
    }

    /** Updates enemy AI to follow and attack player if nearby. */
    public void checkEnemyHostility() {
        for (Enemy enemy : map.getEnemiesOnMap()) {
            Rectangle enemyHostilityBox = new Rectangle(
                    enemy.worldX - enemy.getHostilityArea() / 2,
                    enemy.worldY - enemy.getHostilityArea() / 2,
                    enemy.getHostilityArea(),
                    enemy.getHostilityArea()
            );

            Rectangle enemyAttackRange = new Rectangle(
                    enemy.worldX - enemy.getAttackRange() / 2,
                    enemy.worldY - enemy.getAttackRange() / 2,
                    enemy.getAttackRange(),
                    enemy.getAttackRange()
            );

            if (playerHitbox.intersects(enemyHostilityBox)) {
                enemy.enemyMoveToPlayer(player);
                if (playerHitbox.intersects(enemyAttackRange)) {
                    enemy.enemyAttackPlayer(player);
                }
            } else {
                enemy.moveRandomly();
            }
        }
    }

    /**
     * Quiz mode input handler.
     */
    public void handleLaptopInput(InputController input, Laptop laptop) {
        if (laptop == null || !laptop.isActive()) return;

        if (input.upJustPressed) {
            laptop.moveSelectionUp();
        } else if (input.downJustPressed) {
            laptop.moveSelectionDown();
        } else if (input.enterKeyJustPressed) {
            handleQuizAnswer(laptop);
        } else if (input.escKeyJustPressed) {
            laptop.resetLaptop();
            gameState = GameState.PLAYING;
        }
        input.resetJustPressed();
    }

    /**
     * Checks quiz answer submission and transitions accordingly.
     */
    private void handleQuizAnswer(Laptop laptop) {
        boolean correct = laptop.submitAnswer();
        if (!correct) {
            laptop.resetLaptop();
            gameState = GameState.PLAYING;
        } else if (laptop.getItemState() == ItemState.UNINTERACTABLE) {
            playerHud.setMessage("Quiz complete! You earned an A+!");
            laptop.deactivate();
            spawnItemNearPlayer();
            gameState = GameState.PLAYING;
        } else {
            laptop.resetAnswerSubmitted();
        }
    }

    /**
     * Title screen menu navigation input logic.
     */
    public GameState handleTitleScreenInput(InputController input) {
        if (inputController.downJustPressed | inputController.upJustPressed) {
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

    /** Spawns a sword near the player. */
    private void spawnSwordNearPlayer() {
        int spawnX = player.worldX + GameUI.getTileSize();
        int spawnY = player.worldY + GameUI.getTileSize();
        Sword sword = new Sword();
        int tileX = spawnX / GameUI.getTileSize();
        int tileY = spawnY / GameUI.getTileSize();
        map.addItem(sword, tileX, tileY);
        sword.setX(tileX);
        sword.setY(tileY);
    }

    /** Spawns magic dust near the player. */
    private void spawnDustNearPlayer() {
        int spawnX = player.worldX + GameUI.getTileSize();
        int spawnY = player.worldY + GameUI.getTileSize();
        MagicDust dust = new MagicDust();
        int tileX = spawnX / GameUI.getTileSize();
        int tileY = spawnY / GameUI.getTileSize();
        map.addItem(dust, tileX, tileY);
        dust.setX(tileX);
        dust.setY(tileY);
    }

    /** Spawns either dust or sword depending on what player is missing. */
    private void spawnItemNearPlayer() {
        for (Item item : player.getInventory()) {
            if (item instanceof Sword) {
                spawnDustNearPlayer();
                return;
            }
        }
        spawnSwordNearPlayer();
    }
}
