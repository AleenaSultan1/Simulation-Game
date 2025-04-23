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

import org.team12.controller.GameController;
import org.team12.view.GameUI;

import java.util.Random;

public class Enemy extends Entity {
    public Enemy(GameUI gameUI) {
        super(gameUI);
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

    public void setAction() {
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
}
