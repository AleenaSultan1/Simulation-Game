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

import org.team12.controller.UtilityTool;
import org.team12.model.entities.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityRenderer {
    private Player player;

    private int tileSize;
    public int screenX;
    public int screenY;
    BufferedImage heartFull, heartHalf, heartEmpty;



    public EntityRenderer(int tileSize, Player player) {
        this.player = player;
        this.tileSize = tileSize;



    }

    public void drawEntity(Graphics2D g2, Entity entity) {
        // Calculate where on the screen the entity should appear
        if (entity instanceof Player) {
            screenX = ((Player) entity).getScreenX();
            screenY = ((Player) entity).getScreenY();
            g2.setColor(Color.RED);
            g2.draw(((Player) entity).getAttackRange());

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

