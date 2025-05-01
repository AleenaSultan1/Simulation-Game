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
 * Class: EvilGoon
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.GameController;
import org.team12.states.EnemyStatus;

import java.util.Random;



public class EvilGoon extends Entity{
    private EnemyStatus entityStatus ;
    private int hostilityArea; // the region where it can detect the player (might as well implement collision check)

    public EvilGoon(GameController gameController) {
        super(gameController);

        name = "Evil Goon";
        speed = 3;
        maxLife = 4;
        life = maxLife;
        type = 2;
        this.entityStatus = EnemyStatus.PEACEFUL;



        hitbox.x = 6;
        hitbox.y = 8;
        hitbox.width = 40;
        hitbox.height = 40;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;

        getImage();
    }
    public void getImage() {
        up1 = setup("/evilGoon/enemy_up_1");
        up2 = setup("/evilGoon/enemy_up_2");
        down1 = setup("/evilGoon/enemy_down_1");
        down2 = setup("/evilGoon/enemy_down_2");
        left1 = setup("/evilGoon/enemy_left_1");
        left2 = setup("/evilGoon/enemy_left_2");
        right1 = setup("/evilGoon/enemy_right_1");
        right2 = setup("/evilGoon/enemy_right_2");
    }

    public void setAction() {
        actionLockCounter++;
        // the enemy will do something every 1/2 seconds
        if (actionLockCounter == 30) {
            Random random = new Random();
            // to avoid 0
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
