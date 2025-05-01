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
import org.team12.model.entities.Laptop;


import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerHud {
    GameController gameController;
    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public int commandNumber = 0;

    BufferedImage heartFull, heartHalf, heartEmpty, openLaptop;


    public PlayerHud(GameController gameController) {
        this.gameController = gameController;
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        Item heart = new Heart(gameController);
        Item laptop = new Laptop(gameController);
        heartFull = heart.image;
        heartHalf = heart.image2;
        heartEmpty = heart.image3;
        openLaptop = laptop.image;

    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    // Draws the player's maximum amount of hearts and updates the heart images to reflect the player's current life
    public void drawPlayerLife(Graphics2D g2){
        int x = gameController.tileSize/2;
        int y = gameController.tileSize/2;
        int i = 0;

        // draw available max life (3 hearts)
        while (i<gameController.player.maxLife/2){
            g2.drawImage(heartEmpty, x, y, null);
            i++;
            x += gameController.tileSize;

        }

        //reset
        x = gameController.tileSize/2;
        y = gameController.tileSize/2;
        i = 0;

        //Draw Current life
        while (i<gameController.player.life){
            g2.drawImage(heartHalf, x, y, null);
            i++;
            // if the player has 2 lives (which form one full heart), draw a full heart
            if (i<gameController.player.life){
                g2.drawImage(heartFull, x, y, null);
            }
            i++;
            x += gameController.tileSize;
        }

    }


    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gameController.gameState == gameController.titleState) {
            drawTitleScreen(g2);
        }
        if (gameController.gameState == gameController.playState) {
            // do play stuff later
            drawPlayScreen(g2);
        }
        if (gameController.gameState == gameController.pauseState){
            drawPauseScreen(g2);
        }

    }

    public void drawPlayScreen(Graphics2D g2) {
        // display messages when items or events happen
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(20f));
            g2.drawString(message, gameController.tileSize/2, gameController.tileSize*5);
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

    public void drawTitleScreen(Graphics2D g2) {
        g2.setColor(new Color (10,10,50));
        g2.fillRect(0,0, gameController.screenWidth, gameController.screenHeight);


        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50f));
        String text = "CSCI 205 Simulator";
        int x = centerText(text, g2);
        int y = gameController.tileSize*3;

        // Shadow Text
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Laptop Image
        x=gameController.screenWidth/2 - (gameController.tileSize*2)/2;
        y+=gameController.tileSize*2;
        g2.drawImage(openLaptop, x, y, gameController.tileSize *2, gameController.tileSize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));

        text = "Play Game";
        x = centerText(text, g2);
        y += gameController.tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNumber == 0) {
            g2.drawString(">", x-gameController.tileSize, y);
        }

        text = "Quit";
        x = centerText(text, g2);
        y += gameController.tileSize;
        g2.drawString(text, x, y);
        if(commandNumber == 1) {
            g2.drawString(">", x-gameController.tileSize, y);
        }
    }



    public void drawPauseScreen(Graphics2D g2){
        String text = "PAUSED";
        int x = centerText(text, g2);
        int y = gameController.screenHeight/2;
        g2.drawString (text, x,y);
    }

    public int centerText(String text, Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gameController.screenWidth/2 -length/2;
    }

    // YOU CURED LILY, SHE GAVE YOU AN A+
}


