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
 * Final boss class representing Lily. Inherits from Enemy and overrides
 * behaviors such as sprite drawing and custom state tracking for a boss entity.
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.CollisionController;
import org.team12.states.EnemyStatus;
import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class LilyFinalBoss extends Enemy {
    private EnemyStatus enemyState;     // Tracks Lily's current status
    private int hostilityArea;          // Area within which Lily detects player
    private BufferedImage sprite;       // Current sprite image

    /**
     * Constructs the final boss with given health and detection area.
     * @param hp Lily's maximum health.
     * @param hostilityArea Radius of aggression/awareness.
     */
    public LilyFinalBoss(int hp, int hostilityArea) {
        super(hp, hostilityArea);
        this.hostilityArea = hostilityArea;
        this.enemyState = EnemyStatus.PEACEFUL;
        this.speed = 4;
    }

    /**
     * @return Current behavior state (peaceful, hostile, cured, etc.)
     */
    public EnemyStatus getState() {
        return enemyState;
    }

    /**
     * Updates Lily's state.
     * @param newState New EnemyStatus.
     */
    public void setEnemyState(EnemyStatus newState) {
        this.enemyState = newState;
    }

    /**
     * Sets Lily's world position based on tile coordinates.
     * @param x Tile X
     * @param y Tile Y
     */
    public void setCoord(int x, int y) {
        this.worldX = x * GameUI.getTileSize();
        this.worldY = y * GameUI.getTileSize();
    }

    /**
     * Retrieves Lily's current sprite image based on direction.
     * @return Current directional sprite.
     */
    @Override
    public BufferedImage getCurrentSprite() {
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

    /**
     * Cures Lily by setting her state to CURED.
     */
    public void getCured() {
        super.setEnemyState(EnemyStatus.CURED);
    }
}
