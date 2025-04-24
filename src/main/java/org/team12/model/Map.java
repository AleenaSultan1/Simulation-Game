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
import org.team12.view.GameUI;
import javax.imageio.ImageIO;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Map {
    GameUI gameUI;
    Tile[] tile;

    private Tile[][] grid;
    private List<Item> itemsOnMap;
    private List<Enemy> enemiesOnMap;
    private int width;
    private int height;

//    public Map(int width, int height, gameUI) {
//        this.width = width;
//        this.height = height;
//        this.grid = new Tile[width][height];
//        this.itemsOnMap = new ArrayList<>();
//        this.enemiesOnMap = new ArrayList<>();
//
//
//        generateMap();
//    }

    public Map(GameUI gameUI){
        tile = new Tile[10];
        this.gameUI = gameUI;

        //load tile images
        getTileImage();
    }

    // Gets the images for a tile
    public void getTileImage() {
        try{
            // floor tile
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/floor.png")));
            //wall tile
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draws tiles onto the screen
     * @param g2 java graphics class
     */
    public void draw(Graphics2D g2){
        // adding individual tiles
        //g2.drawImage(tile[0].image, 0, 0, gameUI.tileSize, gameUI.tileSize, null);
        //g2.drawImage(tile[1].image, 48, 48, gameUI.tileSize, gameUI.tileSize, null);
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (col<gameUI.maxScreenCol && row<gameUI.maxScreenRow){
            g2.drawImage(tile[0].image, x, y, gameUI.tileSize, gameUI.tileSize, null);
            col++;
            x+=gameUI.tileSize;
            if (col==gameUI.maxScreenCol){
                col = 0;
                x=0;
                row++;
                y+= gameUI.tileSize;
            }
        }
    }

//    private void generateMap() {
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                grid[x][y] = new Tile(x, y);
//            }
//        }
//    }

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

//    public boolean movePlayer(Player player, int newX, int newY) {
//        if (isInsideBounds(newX, newY)) {
//            player.setXCoordinate(newX);
//            player.setYCoordinate(newY);
//            return true;
//        }
//        return false;
//    }

    private boolean isInsideBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

//    public boolean isOccupied(int x, int y) {
//        return grid[x][y].hasEnemy() || grid[x][y].hasObstacle();
//    }
}

