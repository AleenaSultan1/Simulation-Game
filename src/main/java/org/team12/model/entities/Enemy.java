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

    public Enemy(int hp, int hostilityArea) {
        super(hp);
        this.hostilityArea = GameUI.getTileSize() * 5;
        this.enemyState = EnemyStatus.PEACEFUL;
        this.speed = 0.05;
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

    public void setCoord(int x, int y) {
        this.worldX = x * GameUI.getTileSize();
        this.worldY = y * GameUI.getTileSize();
    }

    public void enemyAttack(Player player) {
        setEnemyState(EnemyStatus.HOSTILE);
        moveToPlayer(player);
    }

    public void moveRandomly() {
        Random rand = new Random();
        int step = rand.nextInt(4);
        switch (step) {
            case 0:
                direction = "up";
                worldY += speed;
                break;
            case 1:
                direction = "down";
                worldY -= speed;

                break;
            case 2:
                direction = "left";
                worldX -= speed;

                break;
            case 3:
                direction = "right";
                worldX += speed;
                break;
            default:
                break;
        }

    }

    public void moveToPlayer(Player player) {
        int dx = player.worldX - this.worldX;
        int dy = player.worldY - this.worldY;

        // Move in the direction of greatest distance first
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
                worldY -= speed;
            } else {
                direction = "up";
                worldY += speed;
            }
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

    public int getHostilityArea() {
        return hostilityArea;
    }

}
