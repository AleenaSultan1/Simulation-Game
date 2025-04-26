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

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    protected int HP;
    protected boolean state; //true=alive
    public int xCoordinate;
    public int yCoordinate;
    // Used for checking collisions/hitboxes
    public Rectangle hitbox;
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;
    public int speed;
    public String direction;

    // Variables to alternate sprites - Creating animations
    public int spriteCounter = 0;
    public int spriteNum =1;



    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Entity(int x, int y, int hp) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.HP = hp;
        this.state = true; // alive by default
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public int getHP() {
        return HP;
    }

    public void takeDamage(int damage) {
        this.HP -= damage;
        if (this.HP <= 0) {
            this.state = false;
        }
    }

    public BufferedImage setup(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Image not found: " + imagePath);
        }
        return image;
    }
}
