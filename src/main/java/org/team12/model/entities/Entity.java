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

import org.team12.controller.GameController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    GameController gameController;
    // Global Coordinates for where an entity is in the world
    public int worldX,worldY;
    // How fast an entity moves (4 pixels)
    public int speed;

    // Used for determining direction for animations
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    // Variables to alternate sprites - Creating animations
    public int spriteCounter = 0;
    public int spriteNum =1;

    // Character Stats and Statuses
    public int actionLockCounter = 0;
    public int maxLife;
    public int life;

    // Used for checking collisions/hitboxes
    public Rectangle hitbox;
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;

    public Entity(GameController gameController){
        this.gameController = gameController;
    }
}
