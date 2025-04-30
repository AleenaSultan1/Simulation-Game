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
 * Class: LilyFinalBoss
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.GameController;
import org.team12.view.GameUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LilyFinalBoss extends Entity {
    GameController gameController;

    public LilyFinalBoss(GameController gameController) {
        super(gameController);
        //super(gameUI, inputController);
        this.gameController = gameController;
        //type = 1;
        name = "Lily The Final Boss";
        speed = 1;
        //maxLife = 10;
        //life = maxLife;
        //attack = 8;
        hitbox.x = 4;
        hitbox.y = 4;
        hitbox.width = 40;
        hitbox.height = 44;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
        //attackArea.width = 48;
        //attackArea.height = 48;

        getImage();
    }
    public void getImage() {
        up1 = setup("/Lily/lily_up_1");
        up2 = setup("/Lily/lily_up_2");
        down1 = setup("/Lily/lily_down_1");
        down2 = setup("/Lily/lily_down_2");
        left1 = setup("/Lily/lily_left_1");
        left2 = setup("/Lily/lily_left_2");
        right1 = setup("/Lily/lily_right_1");
        right2 = setup("/Lily/lily_right_2");
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
        int screenX = worldX - gameController.player.worldX + gameController.player.screenX;
        int screenY = worldY - gameController.player.worldY + gameController.player.screenY;

        if (worldX + gameController.tileSize > gameController.player.worldX - gameController.player.screenX &&
                worldX - gameController.tileSize < gameController.player.worldX + gameController.player.screenX &&
                worldY + gameController.tileSize > gameController.player.worldX - gameController.player.screenX &&
                worldY - gameController.tileSize < gameController.player.worldX + gameController.player.screenX) {

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

            g2.drawImage(image, screenX, screenY, gameController.tileSize, gameController.tileSize, null);
        }
    }
}
