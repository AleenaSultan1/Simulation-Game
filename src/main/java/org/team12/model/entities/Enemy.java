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

import org.team12.controller.InputController;
import org.team12.view.GameUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends Entity {

    GameUI gameUI;

    public Enemy(GameUI gameUI) {
        //super(gameUI, inputController);
        this.gameUI = gameUI;
        type = 1;
        name = "Green monster";
        speed = 1;
        maxLife = 4;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("/enemy/green_slime_original");
        up2 = setup("/enemy/green_slime_with_legs");
        down1 = setup("/enemy/green_slime_original");
        down2 = setup("/enemy/green_slime_with_legs");
        left1 = setup("/enemy/green_slime_original");
        left2 = setup("/enemy/green_slime_with_legs");
        right1 = setup("/enemy/green_slime_original");
        right2 = setup("/enemy/green_slime_with_legs");
    }

    public void update() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gameUI.player.worldX + gameUI.player.screenX;
        int screenY = worldY - gameUI.player.worldY + gameUI.player.screenY;

        if (worldX + gameUI.tileSize > gameUI.player.worldX - gameUI.player.screenX &&
                worldX - gameUI.tileSize < gameUI.player.worldX + gameUI.player.screenX &&
                worldY + gameUI.tileSize > gameUI.player.worldX - gameUI.player.screenX &&
                worldY - gameUI.tileSize < gameUI.player.worldX + gameUI.player.screenX) {

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

            g2.drawImage(image, screenX, screenY, gameUI.tileSize, gameUI.tileSize, null);
        }
    }
}
