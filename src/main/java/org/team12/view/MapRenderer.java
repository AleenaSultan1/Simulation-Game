/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 4/26/2025
 * Time: 4:59 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: MapRenderer
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;

import org.team12.model.Map;
import org.team12.model.Tile;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Item;
import org.team12.model.entities.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class MapRenderer {

    private Map map;
    private BufferedImage floorImage;
    private BufferedImage wallImage;
    private BufferedImage swordImage;
    private BufferedImage enemyImage;
    private int tileSize;
    private Player player;

    public MapRenderer(Player player, Map map, int tileSize) {
        this.map = map;
        this.tileSize = tileSize;
        this.player = player;
        loadImages();
    }

    private void loadImages() {
        try {
            floorImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/stoneFloor.png")));
            wallImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            swordImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sword.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void draw(Graphics2D g2) {
//        for (int x = 0; x < map.getWidth(); x++) {
//            for (int y = 0; y < map.getHeight(); y++) {
//                // worldX/Y is the absolute position of the tile
//                int worldX = x * tileSize;
//                int worldY = y * tileSize;
//
//                // screenX/Y is where the tile appears relative to the player
//                int screenX = worldX - player.worldX + player.screenX;
//                int screenY = worldY - player.worldY + player.screenY;
//
//                // Only draw tiles that are within the visible screen area
//                if (worldX + tileSize > player.worldX - player.screenX &&
//                        worldX - tileSize < player.worldX + player.screenX &&
//                        worldY + tileSize > player.worldY - player.screenY &&
//                        worldY - tileSize < player.worldY + player.screenY) {
//
//                    Tile tile = map.getTile(x, y);
//                    // 1. Draw the wall and floor
//                    if (tile.isObstacle) {
//                        g2.drawImage(wallImage, screenX, screenY, tileSize, tileSize, null);
//                    } else {
//                        g2.drawImage(floorImage, screenX, screenY, tileSize, tileSize, null);
//                    }
//                    // 2. If there is an Item, draw the item
//                    Item item = tile.getItem();
//                    if (item != null) {
//                        g2.drawImage(swordImage, screenX, screenY, tileSize, tileSize, null);
//                    }
//                }
//            }
//        }
//
//    }
}