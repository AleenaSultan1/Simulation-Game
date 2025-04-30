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

import org.team12.states.EnemyStatus;
import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class LilyFinalBoss extends Enemy {
    private EnemyStatus enemyState;
    private int hostilityArea;
    private BufferedImage sprite;

    public LilyFinalBoss(int hp, int hostilityArea) {
        super(hp, hostilityArea);
        this.hostilityArea = hostilityArea;
        this.enemyState = EnemyStatus.PEACEFUL;
        this.speed = 4;
    }

    public EnemyStatus getState() {
        return enemyState;
    }

    public void setEnemyState (EnemyStatus newState) {
        this.enemyState = newState;
    }

    public void setCoord(int x, int y) {
        this.worldX = x * GameUI.getTileSize();
        this.worldY = y * GameUI.getTileSize();
    }

    public void moveRandomly() {
        actionLockCounter++;
        if (actionLockCounter == 15) {
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

    @Override
    public BufferedImage getCurrentSprite() {
        System.out.println("Lily getCurrentSprite() called"); // for debug
        try {
            switch (direction) {
                case "up":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Lily/lily_up_1.png")));
                    break;
                case "down":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Lily/lily_down_1.png")));
                    break;
                case "left":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Lily/lily_left_1.png")));
                    break;
                case "right":
                    sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Lily/lily_right_1.png")));

            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return sprite;
    }

    public void getCured() {
            super.setEnemyState(EnemyStatus.CURED);
        }

    }
