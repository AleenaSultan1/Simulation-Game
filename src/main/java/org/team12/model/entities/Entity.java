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

import org.team12.view.GameUI;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {
    GameUI gameUI;
    public int worldX, worldY; // world means the overall map view
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // for collision detection later
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String dialogues[] = new String[20]; // for the riddles
    int dialogueIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public int type; // 0 = player, 1 = enemy

    // Player State (HP)
    public int maxLife;
    public int life;

    public Entity(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public void setAction() {}

    public void update() {
        setAction();

        collisionOn = false;
        //gameUI.cChecker.checkTile(this);
        //gameUI.cChecker.checkObject(this, false);
        //gameUI.cChecker.checkPlayer(this);
        boolean contactPlayer = gameUI.cChecker.checkPlayer(this); //collision check

        if (this.type == 2 && contactPlayer == true) {
            if (gameUI.player.invincible == false) {
                // damage
                gameUI.player.life -= 1;
                gameUI.player.invincible = true;

        // move player of collision is false
        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        spriteCounter++;
        if (spriteCounter >= 12) {
            if (spriteNum == 1) {
                spriteCounter = 2;
            } else if (spriteNum == 2) {
                spriteCounter = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gameUI.player.worldX + gameUI.player.screenX;
        int screenY = worldY - gameUI.player.worldY + gameUI.player.screenY;

        if (worldX + gameUI.titleSize > gameUI.player.worldX - gameUI.player.screenX &&
                worldX - gameUI.titleSize < gameUI.player.worldX + gameUI.player.screenX &&
                worldX + gameUI.titleSize > gameUI.player.worldX - gameUI.player.screenX &&
                worldX - gameUI.titleSize < gameUI.player.worldX + gameUI.player.screenX &&) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, gameUI.titleSize, gameUI.titleSize, null);
        }
    }

    //public BufferedImage setUp(String imagePath) {
        //Item item = new Item() {
            //@Override
            //public int hashCode() {
                //return super.hashCode();
            }
        }
    }
}
