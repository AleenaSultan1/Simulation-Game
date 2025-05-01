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
 *
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
    private EnemyStatus enemyState;
    private int hostilityArea; // the region where it can detect the player (might as well implement collision check)
    private BufferedImage sprite;
    private int stepLimit = 0;
    private int stepsTaken = 0;
    private final Random rand = new Random();

    public Enemy(int hp, int hostilityArea) {
        super(hp);
        this.hostilityArea = hostilityArea;
        this.enemyState = EnemyStatus.PEACEFUL;
        this.speed = 2;
    }

    public boolean isDead() {
        return this.HP <= 0;
    }

    public EnemyStatus getState() {
        return enemyState;
    }

    public void setEnemyState (EnemyStatus newState) {
        this.enemyState = newState;
    }
    public void setCollisionController(CollisionController collisionController) {
        this.collisionController = collisionController;
    }

    public void setCoord(int x, int y) {
        this.worldX = x * GameUI.getTileSize();
        this.worldY = y * GameUI.getTileSize();
    }

    public void moveRandomly() {
        actionLockCounter++;

        // Decide new direction and step count every 60 ticks (1 second if 60 FPS)
        if (actionLockCounter >= 60 || stepsTaken >= stepLimit) {
            int turn = rand.nextInt(4);
            stepLimit = rand.nextInt(5) + 10; // Move steps in chosen direction
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

        // Try to move if possible
        Entity collided = collisionController.checkEntityCollision(this, dx, dy);
        if (collisionController.canMoveTo(this, dx, dy) & collided == null) {
            worldX += dx;
            worldY += dy;
            stepsTaken++;
        } else {
            // If blocked, immediately pick a new direction
            actionLockCounter = 60;
        }
    }


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

            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return sprite;
    }

}
