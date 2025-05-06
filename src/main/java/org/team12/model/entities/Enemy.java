/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:34 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: Enemy
 *
 * Description:
 * Represents a basic enemy unit that can move randomly or pursue and attack
 * the player when within a defined hostility range.
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.CollisionController;
import org.team12.states.EnemyStatus;
import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Enemy extends Entity {
    private int hostilityArea; // range in which the enemy detects the player
    private BufferedImage sprite;
    private int stepLimit = 0;
    private int stepsTaken = 0;
    private final Random rand = new Random();
    private int attackCounter;
    private int attackRange;

    /**
     * Constructor for an enemy entity.
     * @param hp Initial HP of the enemy.
     * @param hostilityArea Area within which the enemy becomes aggressive.
     */
    public Enemy(int hp, int hostilityArea) {
        super(hp);
        this.hostilityArea = GameUI.getTileSize() * 10;
        this.attackRange = GameUI.getTileSize() * 3;
        this.speed = 2;
        this.attackCounter = 0;
    }

    /**
     * @return Whether the enemy is dead.
     */
    public boolean isDead() {
        return this.getHP() <= 0;
    }

    /**
     * @return Current enemy state (PASSIVE, HOSTILE, DEAD).
     */
    public EnemyStatus getState() {
        return super.currentEntityStatus;
    }

    /**
     * Sets the enemy's current state.
     * @param newState the new enemy state.
     */
    public void setEnemyState (EnemyStatus newState) {
        super.currentEntityStatus = newState;
    }

    /**
     * Assigns the collision controller for movement and collision handling.
     * @param collisionController the shared collision controller instance.
     */
    public void setCollisionController(CollisionController collisionController) {
        this.collisionController = collisionController;
    }

    /**
     * Sets the enemy's world position in tile units.
     * @param x tile X position
     * @param y tile Y position
     */
    public void setCoord(int x, int y) {
        this.worldX = x * GameUI.getTileSize();
        this.worldY = y * GameUI.getTileSize();
    }

    /**
     * Makes the enemy switch to HOSTILE state and chase the player.
     * @param player the target player.
     */
    public void enemyMoveToPlayer(Player player) {
        setEnemyState(EnemyStatus.HOSTILE);
        moveToPlayer(player);
    }

    /**
     * Performs enemy attack logic on the player. Damages every 60 frames.
     * @param player the player to attack.
     */
    public void enemyAttackPlayer(Player player) {
        attackCounter++;
        if (attackCounter >= 60) {
            player.takeDamage(2);
            attackCounter = 0;
            System.out.println("HP: " + player.getHP());
        }
    }

    /**
     * @return Radius around the enemy where attacks are triggered.
     */
    public int getAttackRange() {
        return attackRange;
    }

    /**
     * Moves the enemy in a random direction for a fixed number of steps.
     * Automatically changes direction when blocked.
     */
    public void moveRandomly() {
        actionLockCounter++;

        if (actionLockCounter >= 60 || stepsTaken >= stepLimit) {
            int turn = rand.nextInt(4);
            stepLimit = rand.nextInt(10) + 20;
            stepsTaken = 0;

            switch (turn) {
                case 0 -> direction = "up";
                case 1 -> direction = "down";
                case 2 -> direction = "left";
                case 3 -> direction = "right";
            }

            actionLockCounter = 0;
        }

        int dx = 0;
        int dy = 0;

        switch (direction) {
            case "up" -> dy -= speed;
            case "down" -> dy += speed;
            case "left" -> dx -= speed;
            case "right" -> dx += speed;
        }

        Entity collided = collisionController.checkEntityCollision(this, dx, dy);
        if (collisionController.canMoveTo(this, dx, dy) & collided == null) {
            worldX += dx;
            worldY += dy;
            stepsTaken++;
        } else {
            actionLockCounter = 60;
        }
    }

    /**
     * Loads and returns the correct sprite image based on current direction.
     */
    @Override
    public BufferedImage getCurrentSprite() {
        try {
            switch (direction) {
                case "up":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/evilGoon/enemy_up_1.png")));
                    break;
                case "down":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/evilGoon/enemy_down_1.png")));
                    break;
                case "left":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/evilGoon/enemy_left_1.png")));
                    break;
                case "right":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/evilGoon/enemy_right_1.png")));
                    break;
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return sprite;
    }

    /**
     * Moves enemy toward the player along the axis with the larger distance.
     * Will attempt to move in one direction per update.
     * @param player the player to follow.
     */
    public void moveToPlayer(Player player) {
        actionLockCounter++;
        int dx = 0;
        int dy = 0;

        if (actionLockCounter >= 1) {
            int diffX = player.worldX - this.worldX;
            int diffY = player.worldY - this.worldY;

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (diffX > 0) {
                    direction = "right";
                    dx += speed;
                } else {
                    direction = "left";
                    dx -= speed;
                }
            } else {
                if (diffY > 0) {
                    direction = "down";
                    dy += speed;
                } else {
                    direction = "up";
                    dy -= speed;
                }
            }
            actionLockCounter = 0;
        }

        Entity collided = collisionController.checkEntityCollision(this, dx, dy);
        if (collisionController.canMoveTo(this, dx, dy) & collided == null) {
            worldX += dx;
            worldY += dy;
        }
    }

    /**
     * @return Area around the enemy (in pixels) where player triggers aggression.
     */
    public int getHostilityArea() {
        return hostilityArea;
    }
}
