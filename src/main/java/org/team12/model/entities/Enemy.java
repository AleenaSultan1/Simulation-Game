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
        this.hostilityArea = GameUI.getTileSize() * 3;
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

    public void enemyAttack(Player player) {
        setEnemyState(EnemyStatus.HOSTILE);
//        System.out.println("Hostile");
        moveToPlayer(player);
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

    public void moveToPlayer(Player player) {
        actionLockCounter++;
        int dx = 0;
        int dy = 0;
        // Decide new direction and step count every 60 ticks (1 second if 60 FPS)
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
        // Try to move if possible
        Entity collided = collisionController.checkEntityCollision(this, dx, dy);
        if (collisionController.canMoveTo(this, dx, dy) & collided == null) {
            worldX += dx;
            worldY += dy;
        }

    }

    public void moveToPlayer2(Player player) {
        int dx = player.worldX - this.worldX;
        int dy = player.worldY - this.worldY;

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) {
                direction = "right";
                worldX += speed;
            } else {
                direction = "left";
                worldX -= speed;
            }
        } else {
            if (dy > 0) {
                direction = "down";
                worldY += speed;
            } else {
                direction = "up";
                worldY -= speed;
            }
        }
    }

    public int getHostilityArea() {
        return hostilityArea;
    }

}
