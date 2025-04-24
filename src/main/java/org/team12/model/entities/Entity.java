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

import org.team12.states.EnemyStatus;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    protected EnemyStatus enemyStatus;
    protected int HP;
    protected boolean state; //true=alive
    protected int xCoordinate;
    protected int yCoordinate;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public Entity(int x, int y, int hp) {
        this.enemyStatus = EnemyStatus.PEACEFUL;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.HP = hp;
        this.state = true; // alive by default
    }

    public abstract boolean attack();
    public abstract boolean move();
    public abstract boolean spawn();

    public boolean survivalStatus() {
        return this.state && this.HP > 0;
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
