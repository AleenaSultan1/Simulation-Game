/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 4/22/2025
 * Time: 12:00 AM
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

import org.team12.model.entities.*;

public class Tile {
    private int x;
    private int y;
    private Item item;
    private Enemy enemy;
    private boolean hasObstacle;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.item = null;
        this.enemy = null;
        this.hasObstacle = false;
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

    public boolean hasObstacle() {
        return hasObstacle;
    }

    public void setObstacle(boolean hasObstacle) {
        this.hasObstacle = hasObstacle;
    }
}
