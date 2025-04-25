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
    GameUI gameUI;
    // Used for determining tile type
    public Tile[] tile;
    // Used for loading map data, stores all the map data from a txt file
    public int mapTileNum[][];

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
        // Sets the number of different possible tiles to 10
        tile = new Tile[10];
        this.gameUI = gameUI;
        // 2d array for the map txt
        mapTileNum = new int[gameUI.maxWorldCol][gameUI.maxWorldRow];

        //load tile images
        getTileImage();
        //load map from files
        loadMap("/map/dungeonMap.txt");
        //loadMap("/map/map01.txt");
    }

    public void loadMap(String file){
        try{
            // import map file
            InputStream is = getClass().getResourceAsStream(file);
            // read the contents of the text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // reset the pointer
            int col = 0;
            int row = 0;

            // while there is still room in the array
            while (col<gameUI.maxWorldCol && row<gameUI.maxWorldRow){
                // read the line
                String line = br.readLine();
                while (col<gameUI.maxWorldCol){
                    // split the values to place them into the array
                    String numbers [] = line.split(" ");
                    int num  = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gameUI.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
        br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    // Gets the images for a tile
    public void getTileImage() {
        // sets up a floor
        setup(0, "stoneFloor", false);
        // sets up a wall image that is not passable
        setup(1, "wall", true);
    }

    public void setup(int index, String imageName, boolean isObstacle){
        UtilityTool utilTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = utilTool.scaleImage(tile[index].image, gameUI.tileSize, gameUI.tileSize);
            tile[index].isObstacle = isObstacle;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Draws tiles onto the screen
     * @param g2 java graphics class
     */
    public void draw(Graphics2D g2){
        // set initial variables to start at beginning of text file
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gameUI.maxWorldCol && worldRow < gameUI.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            // Checks the tiles in relation to the world
            int worldX = worldCol * gameUI.tileSize;
            int worldY = worldRow * gameUI.tileSize;
            // This shows where on the screen do we draw
            int screenX = worldX - gameUI.player.worldX + gameUI.player.screenX;
            int screenY = worldY - gameUI.player.worldY + gameUI.player.screenY;

            // Rendering Efficiency: Render only the visible parts on the screen
            if (worldX + gameUI.tileSize > gameUI.player.worldX - gameUI.player.screenX &&
                worldX - gameUI.tileSize < gameUI.player.worldX + gameUI.player.screenX &&
                worldY + gameUI.tileSize > gameUI.player.worldY - gameUI.player.screenY &&
                worldY - gameUI.tileSize < gameUI.player.worldY + gameUI.player.screenY){
                // draw the appropriate sprite for the tile
                g2 .drawImage(tile[tileNum].image, screenX, screenY, null);
            }


            //increment columns
            worldCol++;
            // if we hit the end of the line
            if (worldCol == gameUI.maxWorldCol){
                //reset our pointer to the beginning on the next worldRow
                worldCol = 0;
                worldRow++;
            }
        }
    }


    public void placeItems() {
        gameUI.obj[0] = new Sword();
        gameUI.obj[0].worldX = 3 * gameUI.tileSize;
        gameUI.obj[0].worldY = 3 * gameUI.tileSize;
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

