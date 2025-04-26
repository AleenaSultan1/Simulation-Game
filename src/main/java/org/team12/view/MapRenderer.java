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

    public MapRenderer(Map map, int tileSize) {
        this.map = map;
        this.tileSize = tileSize;
        loadImages();
    }

    private void loadImages() {
        try {
            floorImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/stoneFloor.png")));
            wallImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
            swordImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/sword.png")));
            enemyImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/entities/chest1.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Tile tile = map.getTile(x, y);
                // 1. Draw the floor or wall
                BufferedImage img = tile.hasObstacle() ? wallImage : floorImage;
                g2.drawImage(img, x * tileSize, y * tileSize, tileSize, tileSize, null);

                // 2. If there is an Item, draw the item
                Item item = tile.getItem();
                if (item != null) {
                    g2.drawImage(swordImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
                }

                // 3. If there is an Enemy, draw the enemy
                Enemy enemy = tile.getEnemy();
                if (enemy != null) {
                    g2.drawImage(enemyImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
                }
            }
        }

    }
}