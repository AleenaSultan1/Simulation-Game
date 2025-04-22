/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:33 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: Map
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model;

import org.team12.model.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private Tile[][] grid;
    private List<Item> itemsOnMap;
    private List<Enemy> enemiesOnMap;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new Tile[width][height];
        this.itemsOnMap = new ArrayList<>();
        this.enemiesOnMap = new ArrayList<>();

        generateMap();
    }

    private void generateMap() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new Tile(x, y);
            }
        }
    }

    public void placeItem(Item item, int x, int y) {
        grid[x][y].setItem(item);
        itemsOnMap.add(item);
    }

    public void placeEnemy(Enemy enemy, int x, int y) {
        grid[x][y].setEnemy(enemy);
        enemiesOnMap.add(enemy);
    }

    public Item pickUpItem(int x, int y) {
        Item item = grid[x][y].getItem();
        if (item != null) {
            grid[x][y].setItem(null);
            itemsOnMap.remove(item);
        }
        return item;
    }

    public boolean movePlayer(Player player, int newX, int newY) {
        if (isInsideBounds(newX, newY)) {
            player.setXCoordinate(newX);
            player.setYCoordinate(newY);
            return true;
        }
        return false;
    }

    private boolean isInsideBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isOccupied(int x, int y) {
        return grid[x][y].hasEnemy() || grid[x][y].hasObstacle();
    }
}

