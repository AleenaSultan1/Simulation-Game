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

import org.team12.states.EnemyState;
import org.team12.states.EnemyStatus;
import org.team12.view.GameUI;

public class Enemy extends Entity {
    private EnemyStatus enemyState;
    private int hostilityArea; // the region where it can detect the player (might as well implement collision check)

    public Enemy(int x, int y, int hp, int hostilityArea, GameUI gameUI) {
        super(x, y, hp);
        this.hostilityArea = hostilityArea;
        this.enemyState = EnemyStatus.PEACEFUL;
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
    @Override
    public boolean attack() {
        if (!survivalStatus()) {
            return false;
        }
        System.out.println("Enemy attacked");
        return true;
    }

    @Override
    public boolean move() {
        if (!survivalStatus()) {
            return false;
        }
        System.out.println("Enemy moved");
        return true;
    }

    @Override
    public boolean spawn() {
        System.out.println("Enemy has spawned at (" + xCoordinate + ", " + yCoordinate + ")");
        return true;
    }

    public boolean isDead() {
        return this.HP <= 0;
    }

    public EnemyStatus getState() {
        return enemyState;
    }

    public void setEnemyState (EnemyStatus newState) {
        this.enemyState = newState;
    }

    public int getHostilityArea() {
        return hostilityArea;
    }

    public void setHostilityArea(int hostilityArea) {
        this.hostilityArea = hostilityArea;
    }
}
