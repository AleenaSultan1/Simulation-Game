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

import org.team12.controller.CollisionController;
import org.team12.controller.GameController;
import org.team12.controller.UtilityTool;
import org.team12.model.entities.*;
import org.team12.states.GameState;
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

    private Player player;
    private CollisionController collisionController;


    public Map(String filepath, boolean level2) {
        enemiesOnMap = new ArrayList<>();
        itemsOnMap = new ArrayList<>();
        loadMap(filepath, level2);
    }

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
                    continue; // skip the separator line itself
                }

                // Only load LEVEL2 part if GameState is LEVEL2
                if ((!level2 && !isLevel2Part) ||
                        (level2 && isLevel2Part)) {
                    lines.add(line);
                }
            }

            this.height = lines.size();
            this.width = lines.get(0).split(" ").length;
            grid = new Tile[width][height];

            for (int y = 0; y < height; y++) {
                String[] numbers = lines.get(y).trim().split(" ");
                for (int x = 0; x < numbers.length; x++) {
                    grid[x][y] = new Tile(x, y);
                    int tileType;
                    try {
                        tileType = Integer.parseInt(numbers[x]);
                    } catch (NumberFormatException e) {
                        continue; // skip non-integer tiles in level2 region (e.g. hyphens or dashes)
                    }

                    switch (tileType) {
                        case 1: // Wall
                        case 9:
                            grid[x][y].setObstacle(true);
                            break;
                        case 2: // Enemy (Goon)
                            Enemy enemy = new Enemy(10, 2);
                            enemiesOnMap.add(enemy);
                            enemy.setCoord(x, y);
                            grid[x][y].setEnemy(enemy);
                            break;
                        case 3: // Sword
                            Sword sword = new Sword();
                            itemsOnMap.add(sword);
                            grid[x][y].setItem(sword);
                            sword.setX(x * GameUI.getTileSize());
                            sword.setY(y * GameUI.getTileSize());
                            break;
                        case 4: // Riddle Chest
                            RiddleChest riddleChest = new RiddleChest();
                            itemsOnMap.add(riddleChest);
                            grid[x][y].setItem(riddleChest);
                            riddleChest.setX(x * GameUI.getTileSize());
                            riddleChest.setY(y * GameUI.getTileSize());
                            break;
                        case 5: // Magic Dust
                            MagicDust magicDust = new MagicDust();
                            itemsOnMap.add(magicDust);
                            grid[x][y].setItem(magicDust);
                            magicDust.setX(x * GameUI.getTileSize());
                            magicDust.setY(y * GameUI.getTileSize());
                            break;
                        case 6: // Lily Final Boss
                            LilyFinalBoss lily = new LilyFinalBoss(10, 2);
                            enemiesOnMap.add(lily);
                            lily.setCoord(x, y);
                            grid[x][y].setEnemy(lily);
                            break;
                        default:
                            break;
                    }
                }

            } br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCollisionController(CollisionController collisionController) {
        this.collisionController = collisionController;
        for (Enemy enemy : enemiesOnMap) {
            enemy.setCollisionController(collisionController);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tile getTile (int x, int y){
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return grid[x][y];
        }
        return null;
    }

    public int getWidth () {
        return width;
    }

    public int getHeight () {
        return height;
    }

    public boolean isInsideBounds ( int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public List<Item> getItemsOnMap () {
        return itemsOnMap;
    }

    public List<Enemy> getEnemiesOnMap () {
        return enemiesOnMap;
    }

    public List<Entity> getEntitiesOnMap () {
        List<Entity> entities = new ArrayList<>(enemiesOnMap);
        entities.add(player);
        return entities;
    }

    public void removeItem(Item item) {
        // Find the item's tile by position and clear it
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = grid[x][y];
                if (tile.getItem() == item) {
                    tile.setItem(null);
                    itemsOnMap.remove(item); // precise removal
                    return;
                }
            }
        }
    }

    public void removeEnemy(Enemy enemy) {
        // Find the item's tile by position and clear it
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile = grid[x][y];
                if (tile.getEnemy() == enemy) {
                    tile.setEnemy(null);
                    enemiesOnMap.remove(enemy); // precise removal
                    return;
                }
            }
        }
    }
}
