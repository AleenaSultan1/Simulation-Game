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

import org.team12.controller.GameController;
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
    GameController gameController;
    // Used for determining tile type
    public Tile[] tile;
    // Used for loading map data, stores all the map data from a txt file
    public int mapTileNum[][];

    //Construct a list of potential different objects (30 slots for 30 distinct, unique objects)
    public Item[] obj = new Item[30];
    // Number of monsters that we can display at a time
    public Entity[] monster = new Entity[10];


    public Map(GameController gameController) {
        // Sets the number of different possible tiles to 10
        tile = new Tile[10];
        this.gameController = gameController;
        // 2d array for the map txt
        mapTileNum = new int[gameController.maxWorldCol][gameController.maxWorldRow];

        //load tile images
        getTileImage();
        //load map from files
        loadMap("/map/dungeonMapV2.txt");
    }

    public void loadMap(String file) {
        try {
            // import map file
            InputStream is = getClass().getResourceAsStream(file);
            // read the contents of the text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // reset the pointer
            int col = 0;
            int row = 0;

            // while there is still room in the array
            while (col < gameController.maxWorldCol && row < gameController.maxWorldRow) {
                // read the line
                String line = br.readLine();
                while (col < gameController.maxWorldCol) {
                    // split the values to place them into the array
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gameController.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Gets the images for a tile
    public void getTileImage() {
        // sets up a floor
        setup(0, "floor", false);
        // sets up a wall image that is not passable
        setup(1, "wall2", true);
        setup(2, "fakeWall2", false);
    }

    public void setup(int index, String imageName, boolean isObstacle) {
        UtilityTool utilTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].image = utilTool.scaleImage(tile[index].image, gameController.tileSize, gameController.tileSize);
            tile[index].isObstacle = isObstacle;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws tiles onto the screen
     *
     * @param g2 java graphics class
     */
    public void draw(Graphics2D g2) {
        // set initial variables to start at beginning of text file
        int worldCol = 0;
        int worldRow = 0;
        while (worldCol < gameController.maxWorldCol && worldRow < gameController.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            // Checks the tiles in relation to the world
            int worldX = worldCol * gameController.tileSize;
            int worldY = worldRow * gameController.tileSize;
            // This shows where on the screen do we draw
            int screenX = worldX - gameController.player.worldX + gameController.player.screenX;
            int screenY = worldY - gameController.player.worldY + gameController.player.screenY;

            // Rendering Efficiency: Render only the visible parts on the screen
            if (worldX + gameController.tileSize > gameController.player.worldX - gameController.player.screenX &&
                    worldX - gameController.tileSize < gameController.player.worldX + gameController.player.screenX &&
                    worldY + gameController.tileSize > gameController.player.worldY - gameController.player.screenY &&
                    worldY - gameController.tileSize < gameController.player.worldY + gameController.player.screenY) {
                // draw the appropriate sprite for the tile
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }


            //increment columns
            worldCol++;
            // if we hit the end of the line
            if (worldCol == gameController.maxWorldCol) {
                //reset our pointer to the beginning on the next worldRow
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

