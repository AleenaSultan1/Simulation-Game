/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:33 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: Entity
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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {

    protected EnemyStatus currentEntityStatus;
    protected int HP;
    private final int MaxHP;
    protected boolean state; //true=alive
    public int worldX;
    public int worldY;
    // Used for checking collisions/hitboxes
    protected CollisionController collisionController;
    protected Rectangle hitbox;
    protected int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;
    public double speed;
    public String direction;

    // Variables to alternate sprites - Creating animations
    public int spriteCounter = 0;
    public int spriteNum =1;
    public int actionLockCounter = 0;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Entity(int hp) {
        this.MaxHP = hp;
        this.HP = hp;
        this.currentEntityStatus = EnemyStatus.PEACEFUL;
        hitbox = new Rectangle();
        hitboxDefaultX = 8; // Could be adjusted
        hitboxDefaultY = 8;
        hitbox.x = hitboxDefaultX;
        hitbox.y = hitboxDefaultY;
        hitbox.width = GameUI.getTileSize() - 16;
        hitbox.height = GameUI.getTileSize() - 16;
    }

    public int getMaxHP() {
        return MaxHP;
    }

    public int getworldX() {
        return worldX;
    }

    public int getworldY() {
        return worldY;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public int getHP() {
        return HP;
    }

    public void takeDamage(int damage) {
        this.HP -= damage;
        if (this.HP <= 0) {
            currentEntityStatus = EnemyStatus.DEAD;
            System.out.println("IS DEAD");
        }
    }

    // Method to be overriden
    public abstract BufferedImage getCurrentSprite();
}

