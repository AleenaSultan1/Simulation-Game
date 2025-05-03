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

        Item heart = new Heart();

        heartFull = UtilityTool.scaleImage(heart.image, tileSize, tileSize);
        heartHalf = UtilityTool.scaleImage(heart.image2, tileSize, tileSize);
        heartEmpty = UtilityTool.scaleImage(heart.image3, tileSize, tileSize);

    }

    public void drawEntity(Graphics2D g2, Entity entity) {
        int drawX, drawY;

        if (entity instanceof Player) {
            drawX = ((Player) entity).getScreenX();
            drawY = ((Player) entity).getScreenY();

            // Draw the attack range relative to the screen
            Rectangle attack = ((Player) entity).getAttackRange();
            int drawRangeX = attack.x - player.worldX + player.getScreenX();
            int drawRangeY = attack.y - player.worldY + player.getScreenY();

            g2.setColor(new Color(255, 0, 0, 100)); // Transparent red
            g2.fillRect(drawRangeX, drawRangeY, attack.width, attack.height);

        } else {
            drawX = entity.worldX - player.worldX + player.getScreenX();
            drawY = entity.worldY - player.worldY + player.getScreenY();
        }

        // Only draw if on screen
        if (entity.worldX + tileSize > player.worldX - player.getScreenX() &&
                entity.worldX - tileSize < player.worldX + player.getScreenX() &&
                entity.worldY + tileSize > player.worldY - player.getScreenY() &&
                entity.worldY - tileSize < player.worldY + player.getScreenY()) {

            BufferedImage image = entity.getCurrentSprite();
            if (image != null) {
                g2.drawImage(image, drawX, drawY, tileSize, tileSize, null);
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


    public void drawPlayerLife(Graphics2D g2){
        int x = tileSize/2;
        int y = tileSize/2;
        int i = 0;

        // draw available max life (3 hearts)
        while (i < player.getMaxHP()/2){
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += tileSize;

        }

        //reset
        x = tileSize/2;
        y = tileSize/2;
        i = 0;

        //Draw Current life
        while (i < player.getHP()){
            g2.drawImage(heartHalf, x, y, null);
            i++;
            // if the player has 2 lives (which form one full heart), draw a full heart
            if (i < player.getHP()){
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += tileSize;
        }

    }

}

