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
        if (entity instanceof Player) {
            screenX = ((Player) entity).getScreenX();
            screenY = ((Player) entity).getScreenY();

        } else {
            screenX = entity.worldX - player.worldX + player.getScreenX();
            screenY = entity.worldY - player.worldY + player.getScreenY();
        }
// Only draw tiles that are within the visible screen area
        if (entity.worldX + tileSize > player.worldX - player.getScreenX() &&
            entity.worldX - tileSize < player.worldX + player.getScreenX() &&
            entity.worldY + tileSize > player.worldY - player.getScreenY() &&
            entity.worldY - tileSize < player.worldY + player.getScreenY()) {

        BufferedImage image = entity.getCurrentSprite(); // Assume each Entity can provide its current sprite based on direction/animation
        if (image != null) {
//            System.out.printf("Drawing entity at (%d, %d), player at (%d, %d)\n",
//                    entity.worldX, entity.worldY, player.worldX, player.worldY);
            g2.drawImage(image, screenX, screenY, tileSize, tileSize, null);
        }
        }
    }
}

