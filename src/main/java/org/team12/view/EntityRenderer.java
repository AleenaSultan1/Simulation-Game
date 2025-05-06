/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Khanh Cao
 * Date: 4/26/2025
 * Time: 10:06 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.view
 * Class: EntityRenderer
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;

import org.team12.model.entities.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Handles rendering of game entities (players, enemies, etc.) to the screen.
 * Manages entity sprites, positions, and health bar displays.
 */
public class EntityRenderer {
    /** Reference to the player for relative positioning calculations */
    private final Player player;

    /** Size of each tile in pixels, used for scaling and positioning */
    private final int tileSize;

    /**
     * Constructs an EntityRenderer with specified tile size and player reference.
     * @param tileSize The size of each game tile in pixels
     * @param player The player instance used for relative positioning
     */
    public EntityRenderer(int tileSize, Player player) {
        this.player = player;
        this.tileSize = tileSize;
    }

    /**
     * Draws an entity on the screen at its current position.
     * Only renders entities that are within the visible screen area.
     * @param g2 The Graphics2D context to draw with
     * @param entity The entity to be rendered
     */
    public void drawEntity(Graphics2D g2, Entity entity) {
        int drawX, drawY;

        if (entity instanceof Player) {
            drawX = ((Player) entity).getScreenX();
            drawY = ((Player) entity).getScreenY();
        } else {
            drawX = entity.worldX - player.worldX + player.getScreenX();
            drawY = entity.worldY - player.worldY + player.getScreenY();
        }

        // Only draw if on screen
        if (entity.worldX + tileSize > player.worldX - player.getScreenX() &&
                entity.worldX - tileSize < player.worldX + player.getScreenX() &&
                entity.worldY + tileSize > player.worldY - player.getScreenY() &&
                entity.worldY - tileSize < player.worldY + player.getScreenY()) {

            BufferedImage image = entity.getCurrentSprite(); // Entity can provide its current sprite based on direction/animation
            image = UtilityTool.scaleImage(image, tileSize*2, tileSize*2);
            g2.drawImage(image, drawX, drawY, tileSize, tileSize, null);
        }
    }

    /**
     * Draws an enemy's health bar above their sprite.
     * Only renders health bars for enemies visible on screen.
     * @param g2 The Graphics2D context to draw with
     * @param enemy The enemy whose health to display
     */
    public void drawEnemyHP(Graphics2D g2, Enemy enemy) {
        // Step 1: Convert worldX/Y to screenX/Y
        int screenX = enemy.worldX - player.worldX + player.getScreenX();
        int screenY = enemy.worldY - player.worldY + player.getScreenY();

        // Step 2: Only draw if on screen
        if (enemy.worldX + tileSize > player.worldX - player.getScreenX() &&
                enemy.worldX - tileSize < player.worldX + player.getScreenX() &&
                enemy.worldY + tileSize > player.worldY - player.getScreenY() &&
                enemy.worldY - tileSize < player.worldY + player.getScreenY()) {

            // Step 3: Draw background of HP bar (gray)
            int barWidth = tileSize;
            int barHeight = 5;
            int barX = screenX;
            int barY = screenY - 10; // Slightly above enemy head

            g2.setColor(Color.BLACK);
            g2.fillRect(barX, barY, barWidth, barHeight);

            // Step 4: Draw actual HP portion (red)
            float hpRatio = (float) enemy.getHP() / enemy.getMaxHP();
            int filledWidth = (int) (barWidth * hpRatio);

            g2.setColor(Color.RED);
            g2.fillRect(barX, barY, filledWidth, barHeight);

            // Optional: Border
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2)); // Thickness in pixels
            g2.drawRect(barX, barY, barWidth, barHeight);
        }
    }
}

