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
import org.team12.states.ItemState;
import org.team12.view.GameUI;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Map {
    private Tile[][] grid;
    private List<Item> itemsOnMap;
    private List<Enemy> enemiesOnMap;

    private int width;
    private int height;


    public Map(String filepath) {
        enemiesOnMap = new ArrayList<>();
        itemsOnMap = new ArrayList<>();
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
                    // Wall
                    switch (tileType) {
                        case 1:
                            grid[x][y].setObstacle(true);
                            break;
                        // Goon
                        case 2:
                            Enemy enemy = new Enemy(10, 2);
                            enemiesOnMap.add(enemy);
                            enemy.setCoord(x, y);
                            grid[x][y].setEnemy(enemy);
                            break;
                        // Sword
                        case 3:
                            Sword sword = new Sword();
                            itemsOnMap.add(sword);
                            grid[x][y].setItem(sword);
                            itemsOnMap.getLast().setX(x * GameUI.getTileSize());
                            itemsOnMap.getLast().setY(y * GameUI.getTileSize());
                            break;

                        case 4:
                            RiddleChest riddleChest = new RiddleChest();
                            itemsOnMap.add(riddleChest);
                            grid[x][y].setItem(riddleChest);
                            itemsOnMap.getLast().setX(x * GameUI.getTileSize());
                            itemsOnMap.getLast().setY(y * GameUI.getTileSize());
                            break;

                        case 5:
                            MagicDust magicDust = new MagicDust();
                            itemsOnMap.add(magicDust);
                            grid[x][y].setItem(magicDust);
                            itemsOnMap.getLast().setX(x * GameUI.getTileSize());
                            itemsOnMap.getLast().setY(y * GameUI.getTileSize());
                            break;

                        default:
                            break;
                    }

                }
                y++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public boolean isInsideBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public List<Item> getItemsOnMap() {
        return itemsOnMap;
    }

    public List<Enemy> getEnemiesOnMap() {
        return enemiesOnMap;
    }

    public void removeItemsOnMap() {
        for (int x =  0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = grid[x][y];
                if (tile.hasItem() && tile.getItem().getItemState() == ItemState.UNINTERACTABLE) {
                    tile.setItem(null);

                }
            }
        }
    }

}

