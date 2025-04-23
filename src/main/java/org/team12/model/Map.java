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
    public List<Item> itemsOnMap;
    public List<Enemy> enemiesOnMap;
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

                // Build outline wall
                if (x == 0 | y == 0 | x == width - 1 | y == height - 1) {
                    grid[x][y].setObstacle(true);
                }
            }
        }

        //
        for (int y = 1; y < height; y++) {
            // Level 1/2
            grid[width - 3][y].setObstacle(true);

            // Level 2/3
            grid[width - 2][y].setObstacle(true);
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
        return grid[x][y].isOccupied();
    }
}

