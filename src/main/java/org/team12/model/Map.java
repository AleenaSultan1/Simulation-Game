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
 * The Map class represents the tile-based game world. It stores information about
 * tiles, enemies, and items on the map, and handles level loading and entity placement.
 * ****************************************
 */

package org.team12.model;

import org.team12.controller.CollisionController;
import org.team12.model.items.Item;
import org.team12.model.items.Laptop;
import org.team12.model.entities.*;

import java.io.BufferedReader;
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
    private Player player;
    private CollisionController collisionController;

    /**
     * Constructs a Map object and loads tile/entity data from a map file
     * @param filepath Path to the tile map text file
     * @param level2 Whether to load the second section of the map
     */
    public Map(String filepath, boolean level2) {
        enemiesOnMap = new ArrayList<>();
        itemsOnMap = new ArrayList<>();
        loadMap(filepath, level2);
    }

    /**
     * Loads the map data from a file. Handles tile generation and entity/item placement.
     * @param filepath The path to the tile data
     * @param level2 Whether to load the LEVEL2 portion of the map
     */
    public void loadMap(String filepath, boolean level2) {
        enemiesOnMap.clear();
        itemsOnMap.clear();

        try {
            InputStream is = getClass().getResourceAsStream(filepath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            List<String> lines = new ArrayList<>();
            String line;
            boolean isLevel2Part = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().startsWith("*")) {
                    isLevel2Part = true;
                    continue;
                }

                // Load appropriate section of map depending on level2 flag
                if ((!level2 && !isLevel2Part) || (level2 && isLevel2Part)) {
                    lines.add(line);
                }
            }

            this.height = lines.size();
            this.width = lines.get(0).split(" ").length;
            grid = new Tile[width][height];

            // Populate tiles and place enemies/items based on tile values
            for (int y = 0; y < height; y++) {
                String[] numbers = lines.get(y).trim().split(" ");
                for (int x = 0; x < numbers.length; x++) {
                    grid[x][y] = new Tile(x, y);
                    int tileType;
                    try {
                        tileType = Integer.parseInt(numbers[x]);
                    } catch (NumberFormatException e) {
                        continue; // skip symbols or malformed tiles
                    }

                    switch (tileType) {
                        case 1: case 9:
                            grid[x][y].setObstacle(true);
                            break;
                        case 2: // Normal enemy
                            Enemy enemy = new Enemy(10, 2);
                            enemiesOnMap.add(enemy);
                            enemy.setCoord(x, y);
                            grid[x][y].setEnemy(enemy);
                            break;
                        case 4: // Laptop (quiz chest)
                            Laptop laptop = new Laptop();
                            itemsOnMap.add(laptop);
                            grid[x][y].setItem(laptop);
                            laptop.setX(x);
                            laptop.setY(y);
                            break;
                        case 6: // Final boss
                            LilyFinalBoss lily = new LilyFinalBoss(10, 2);
                            enemiesOnMap.add(lily);
                            lily.setCoord(x, y);
                            grid[x][y].setEnemy(lily);
                            break;
                        default:
                            break;
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Assigns the collision controller to all enemies on the map
     */
    public void setCollisionController(CollisionController collisionController) {
        this.collisionController = collisionController;
        for (Enemy enemy : enemiesOnMap) {
            enemy.setCollisionController(collisionController);
        }
    }

    /**
     * Assigns the player reference to the map
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Retrieves the tile at the specified coordinates
     * @param x Column index
     * @param y Row index
     * @return The Tile object or null if out of bounds
     */
    public Tile getTile(int x, int y){
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    public int getWidth () { return width; }
    public int getHeight () { return height; }

    /**
     * Checks if a coordinate lies within the map boundaries
     */
    public boolean isInsideBounds (int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public List<Item> getItemsOnMap () { return itemsOnMap; }
    public List<Enemy> getEnemiesOnMap () { return enemiesOnMap; }

    /**
     * Returns all entities on the map including enemies and the player
     */
    public List<Entity> getEntitiesOnMap () {
        List<Entity> entities = new ArrayList<>(enemiesOnMap);
        entities.add(player);
        return entities;
    }

    /**
     * Adds an item to a specific tile on the map
     */
    public void addItem (Item item, int x, int y) {
        Tile targetTile = getTile(x, y);
        if (targetTile != null) {
            itemsOnMap.add(item);
            grid[x][y].setItem(item);
        }
    }

    /**
     * Removes the given item from both the map and tile grid
     */
    public void removeItem(Item item) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = grid[x][y];
                if (tile.getItem() == item) {
                    tile.setItem(null);
                    itemsOnMap.remove(item);
                    return;
                }
            }
        }
    }

    /**
     * Removes the given enemy from both the map and tile grid
     */
    public void removeEnemy(Enemy enemy) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = grid[x][y];
                if (tile.getEnemy() == enemy) {
                    tile.setEnemy(null);
                    enemiesOnMap.remove(enemy);
                    return;
                }
            }
        }
    }
}
