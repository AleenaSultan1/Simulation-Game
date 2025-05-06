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

import org.team12.model.items.Heart;
import org.team12.model.items.Item;
import org.team12.model.Map;
import org.team12.model.Tile;
import org.team12.model.entities.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.List;
/**
 * Handles rendering of the game map including tiles, items, and player HUD elements.
 * Manages the conversion between world coordinates and screen coordinates for proper rendering.
 */
public class MapRenderer {
    /** Reference to the game map */
    private Map map;

    /** Image for floor tiles */
    private BufferedImage floorImage;

    /** Image for wall tiles */
    private BufferedImage wallImage;

    /** Images for player health display */
    private BufferedImage heartFull, heartHalf, heartEmpty;

    /** Size of each tile in pixels */
    private final int tileSize;

    /** Reference to the player for positioning calculations */
    private final Player player;

    /**
     * Constructs a MapRenderer with specified player, map, and tile size.
     * @param player The player instance used for relative positioning
     * @param map The game map to render
     * @param tileSize The size of each tile in pixels
     */
    public MapRenderer(Player player, Map map, int tileSize) {
        this.map = map;
        this.tileSize = tileSize;
        this.player = player;
        loadImages();
    }

    /**
     * Loads all required images for rendering including tiles and health indicators.
     * @throws RuntimeException if any image fails to load
     */
    private void loadImages() {
        try {
            floorImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/325floor.png")));
            wallImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/325wall.png")));

            Heart heart = new Heart();
            heartFull = UtilityTool.scaleImage(heart.getFullHeart(), tileSize, tileSize);
            heartHalf = UtilityTool.scaleImage(heart.getHalfHeart(), tileSize, tileSize);
            heartEmpty = UtilityTool.scaleImage(heart.getEmptyHeart(), tileSize, tileSize);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load map rendering assets");
        }
    }

    /**
     * Renders the entire map including tiles and items, only drawing visible portions.
     * @param g2 The Graphics2D context to draw with
     */
    public void draw(Graphics2D g2) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                // worldX/Y is the absolute position of the tile
                int worldX = x * tileSize;
                int worldY = y * tileSize;

                // screenX/Y is where the tile appears relative to the player
                int screenX = worldX - player.worldX + player.getScreenX();
                int screenY = worldY - player.worldY + player.getScreenY();

                // Only draw tiles that are within the visible screen area
                if (worldX + tileSize > player.worldX - player.getScreenX() &&
                        worldX - tileSize < player.worldX + player.getScreenX() &&
                        worldY + tileSize > player.worldY - player.getScreenY() &&
                        worldY - tileSize < player.worldY + player.getScreenY()) {

                    Tile tile = map.getTile(x, y);
                    // 1. Draw the wall and floor
                    if (tile == null) {
                        continue;
                    }
                    if (tile.isObstacle) {
                        g2.drawImage(wallImage, screenX, screenY, tileSize, tileSize, null);
                    } else {
                        g2.drawImage(floorImage, screenX, screenY, tileSize, tileSize, null);
                    }
                    // 2. If there is an Item, draw the item
                    Item item = tile.getItem();
                    if (item != null) {
                        g2.drawImage(item.getSprite()
                                , screenX, screenY, tileSize, tileSize, null);
                    }
                }
            }
        }
        drawPlayerLife(g2);
        drawInventory(g2);
    }

    /**
     * Renders the player's inventory items in the HUD.
     * @param g2 The Graphics2D context to draw with
     */
    public void drawInventory(Graphics2D g2) {
        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) return;

        int startX = tileSize / 2;
        int startY = tileSize / 2 + tileSize * 2; // Position below hearts

        // Draw inventory title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
        g2.setColor(Color.WHITE);
        g2.drawString("Inventory:", startX, startY - 25);

        // Draw inventory items
        int itemX = startX;
        int itemY = startY - 15;
        int itemsPerRow = 5; // Max items per row before wrapping
        int spacing = 5; // Space between items

        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            BufferedImage sprite = item.getSprite();

            if (sprite != null) {
                // Scale down inventory items slightly
                int scaledSize = tileSize - 10;
                g2.drawImage(sprite, itemX, itemY, scaledSize, scaledSize, null);

                // Move position for next item
                itemX += scaledSize + spacing;

                // Wrap to next row if needed
                if ((i + 1) % itemsPerRow == 0) {
                    itemX = startX;
                    itemY += scaledSize + spacing;
                }
            }
        }
    }

    /**
     * Renders the player's health as heart icons in the HUD.
     * @param g2 The Graphics2D context to draw with
     */
    public void drawPlayerLife(Graphics2D g2) {
        int x = tileSize/2;
        int y = tileSize/2;
        int i = 0;

        // draw available max life (3 hearts)
        while (i < player.getMaxHP()/2) {
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += tileSize;
        }

        //reset
        x = tileSize/2;
        y = tileSize/2;
        i = 0;

        //Draw Current life
        while (i < player.getHP()) {
            g2.drawImage(heartHalf, x, y, null);
            i++;
            // if the player has 2 lives (which form one full heart), draw a full heart
            if (i < player.getHP()) {
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += tileSize;
        }
    }
}
