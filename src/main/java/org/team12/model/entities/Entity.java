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
 * Abstract base class representing any movable or interactive game entity.
 * Handles core attributes like health, position, collision, and animation.
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
    private int HP;                  // Current health
    private final int MaxHP;        // Max health
    protected EnemyStatus currentEntityStatus;

    public int worldX;              // X position in world coordinates
    public int worldY;              // Y position in world coordinates

    protected CollisionController collisionController;  // Reference for collision checks
    protected Rectangle hitbox;    // Hitbox for collision detection
    protected int hitboxDefaultX, hitboxDefaultY;

    public boolean collisionOn = false;  // Flag if collision is detected
    public double speed;           // Movement speed
    public String direction;       // Current facing direction

    // Sprite animation support
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;

    // Directional sprite frames
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    /**
     * Constructor for a generic entity.
     * @param hp Maximum and starting health for the entity.
     */
    public Entity(int hp) {
        this.MaxHP = hp;
        this.HP = hp;
        this.currentEntityStatus = EnemyStatus.PEACEFUL;

        hitbox = new Rectangle();
        hitboxDefaultX = 8;
        hitboxDefaultY = 8;
        hitbox.x = hitboxDefaultX;
        hitbox.y = hitboxDefaultY;
        hitbox.width = GameUI.getTileSize() - 16;
        hitbox.height = GameUI.getTileSize() - 16;
    }

    /** @return The entity's maximum health. */
    public int getMaxHP() {
        return MaxHP;
    }

    /** @return Current X position in world coordinates. */
    public int getworldX() {
        return worldX;
    }

    /** @return Current Y position in world coordinates. */
    public int getworldY() {
        return worldY;
    }

    /** @return Hitbox used for collision detection. */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /** @return The entity's current health. */
    public int getHP() {
        return HP;
    }

    /**
     * Apply damage to the entity.
     * If HP falls to zero or below, set state to DEAD.
     * @param damage Amount of damage to apply.
     */
    public void takeDamage(int damage) {
        this.HP -= damage;
        if (this.HP <= 0) {
            currentEntityStatus = EnemyStatus.DEAD;
            System.out.println("IS DEAD");
        }
    }

    /**
     * Abstract method to retrieve the current sprite for rendering.
     * Must be implemented by subclasses.
     * @return The appropriate sprite image.
     */
    public abstract BufferedImage getCurrentSprite();
}
