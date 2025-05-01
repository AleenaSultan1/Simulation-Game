/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/25/25
 * Time: 9:47 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.view
 * Class: PlayerHud
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;

import org.team12.controller.GameController;
import org.team12.model.entities.Heart;
import org.team12.model.entities.Item;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerHud { ;
    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    private final int playerMaxLife = 6;

    BufferedImage heartFull, heartHalf, heartEmpty;


    public PlayerHud() {
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        Heart heart = new Heart();
        heartFull = heart.image;
        heartHalf = heart.getHalfHeart();
        heartEmpty = heart.getEmptyHeart();

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

//    public void drawPlayerLife(Graphics2D g2){
//        int x = GameUI.getTileSize()/2;
//        int y = GameUI.getTileSize()/2;
//        int i = 0;
//
//        // draw available max life (3 hearts)
//        while ( i <  gameController.player.maxLife/2 ){
//            g2.drawImage(heartEmpty, x, y, null);
//            i++;
//            x += GameUI.getTileSize()/2;
//
//        }
//
//        //reset
//        x = GameUI.getTileSize()/2;
//        y = GameUI.getTileSize()/2;
//        i = 0;
//
//        //Draw Current life
//        while (i<gameController.player.life){
//            g2.drawImage(heartHalf, x, y, null);
//            i++;
//            // if the player has 2 lives (which form one full heart), draw a full heart
//            if (i<gameController.player.life){
//                g2.drawImage(heartFull, x, y, null);
//            }
//            i++;
//            x += GameUI.getTileSize()/2;
//        }
//
//    }

    public void drawPlayerLife(Graphics2D g2){
        int tileSize = GameUI.getTileSize();
        int x = tileSize / 2;
        int y = tileSize / 2;

        int maxHearts = playerMaxLife / 2;
        int currentLife = playerMaxLife;

        // Draw empty hearts as background
        for (int i = 0; i < maxHearts; i++) {
            g2.drawImage(heartEmpty, x + (i * (tileSize / 2)), y, null);
        }

        // Draw full and half hearts over the empty ones
        x = tileSize / 2;
        for (int i = 0; i < currentLife / 2; i++) {
            g2.drawImage(heartFull, x + (i * (tileSize / 2)), y, null);
        }

        // If there's a remaining half heart
        if (currentLife % 2 == 1) {
            g2.drawImage(heartHalf, x + ((currentLife / 2) * (tileSize / 2)), y, null);
        }
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        // display messages when items or events happen
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(20f));
            g2.drawString(message, GameUI.getTileSize()/2, GameUI.getTileSize()*5);
            messageTimer++;

            // Displays the message for 2 seconds
            if (messageTimer > 120){
                messageTimer = 0;
                messageOn = false;
            }
        }

        // draw hearts for the player
        drawPlayerLife(g2);

    }

}


