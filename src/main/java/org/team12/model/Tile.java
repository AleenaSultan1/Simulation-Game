/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/22/25
 * Time: 8:20 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: Tile
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model;

import org.team12.model.Items.Item;
import org.team12.model.entities.*;

import java.awt.image.BufferedImage;


public class Tile {

    //used to generate an image for a particular tile
    BufferedImage image;
    // Used for collision detection
    public boolean isObstacle;

    private int x;
    private int y;
    private Item item;
    private Enemy enemy;
    private LilyFinalBoss lilyFinalBoss;


    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.item = null;
        this.enemy = null;
        this.isObstacle = false;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public LilyFinalBoss getLilyFinalBoss() {
        return lilyFinalBoss;
    }

    public boolean hasObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean hasObstacle) {
        this.isObstacle = hasObstacle;
    }

    public boolean isOccupied() {
        return (item != null | enemy != null | !isObstacle);
    }

    public boolean hasItem() {
        return item != null;
    }
}

