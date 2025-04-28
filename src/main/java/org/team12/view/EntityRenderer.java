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

import org.team12.model.entities.Entity;
import org.team12.model.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityRenderer {

    private int tileSize;
    public int screenX;
    public int screenY;


    public EntityRenderer(int tileSize) {
        this.tileSize = tileSize;
    }

    public void drawEntity(Graphics2D g2, Entity entity, Player player) {
        // Calculate where on the screen the entity should appear
        if (entity.getClass() == Player.class) {
            screenX = ((Player) entity).screenX;
            screenY = ((Player) entity).screenY;

        } else {
            screenX = entity.worldX - player.worldX + player.screenX;
            screenY = entity.worldY - player.worldY + player.screenY;
        }
        // Only draw tiles that are within the visible screen area
        if (entity.worldX + tileSize > player.worldX - player.screenX &&
                entity.worldX - tileSize < player.worldX + player.screenX &&
                entity.worldY + tileSize > player.worldY - player.screenY &&
                entity.worldY - tileSize < player.worldY + player.screenY) {

            BufferedImage image = entity.getCurrentSprite(); // Assume each Entity can provide its current sprite based on direction/animation
            if (image != null) {
                g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
            }
        }
    }
}


