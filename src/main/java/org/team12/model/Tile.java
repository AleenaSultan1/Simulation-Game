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
 * Represents a single tile on the game map. Each tile may hold an item,
 * an enemy, or be marked as an obstacle for collision detection.
 * ****************************************
 */

package org.team12.model;

import org.team12.model.items.Item;
import org.team12.model.entities.*;

import java.awt.image.BufferedImage;

public class Tile {

    /** Optional background image for the tile */
    BufferedImage image;

    /** True if this tile blocks movement (e.g., wall or obstacle) */
    public boolean isObstacle;

    private int x;
    private int y;
    private Item item;
    private Enemy enemy;
    private LilyFinalBoss lilyFinalBoss;

    /**
     * Constructs a tile at the given (x, y) coordinate.
     * @param x Tile column index
     * @param y Tile row index
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.item = null;
        this.enemy = null;
        this.isObstacle = false;
    }

    /** @return The item currently on the tile, or null if none */
    public Item getItem() {
        return item;
    }

    /** Sets an item to be placed on this tile */
    public void setItem(Item item) {
        this.item = item;
    }

    /** @return The enemy occupying this tile, or null */
    public Enemy getEnemy() {
        return enemy;
    }

    /** Places an enemy on this tile */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /** @return Reference to the LilyFinalBoss if present */
    public LilyFinalBoss getLilyFinalBoss() {
        return lilyFinalBoss;
    }

    /** @return True if this tile blocks movement */
    public boolean hasObstacle() {
        return isObstacle;
    }

    /** Marks this tile as an obstacle or walkable */
    public void setObstacle(boolean hasObstacle) {
        this.isObstacle = hasObstacle;
    }

    /**
     * Checks if tile is logically occupied.
     * @return true if it has any content or is not blocked
     */
    public boolean isOccupied() {
        return (item != null | enemy != null | !isObstacle);
    }

    /** @return true if the tile has an item on it */
    public boolean hasItem() {
        return item != null;
    }
}
