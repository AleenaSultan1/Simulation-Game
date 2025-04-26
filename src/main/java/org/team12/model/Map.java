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

import org.team12.controller.UtilityTool;
import org.team12.model.entities.*;
import org.team12.view.GameUI;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class Map {
    public Tile[] tile;
    private Tile[][] grid;
    private List<Item> itemsOnMap;
    private List<Enemy> enemiesOnMap;

    private int width;
    private int height;

    public Map(String filepath) {
        loadMap(filepath);
    }
    private void loadMap(String filepath) {
        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            // First, read all lines to determine dimensions
            String line;
            int rows = 0;
            int cols = 0;
            while ((line = br.readLine()) != null) {
                if (rows == 0) {
                    cols = line.split(" ").length;
                }
                rows++;
            }
            this.width = cols;
            this.height = rows;

            // Initialize grid
            grid = new Tile[width][height];

            // Reset reader to beginning
            is = getClass().getResourceAsStream(filepath);
            br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int y = 0;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                for (int x = 0; x < numbers.length; x++) {
                    grid[x][y] = new Tile(x, y);
                    int tileType = Integer.parseInt(numbers[x]);
                    if (tileType == 1) {
                        grid[x][y].setObstacle(true);
                    }
                    // tileType 0 means walkable floor
                    // You can add more types later if you want
                }
                y++;
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    public Tile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private boolean isInsideBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isOccupied(int x, int y) {
        return grid[x][y].hasEnemy() || grid[x][y].hasObstacle();
    }
}

