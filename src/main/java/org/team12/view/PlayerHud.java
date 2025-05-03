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
import org.team12.controller.InputController;
import org.team12.controller.UtilityTool;
import org.team12.model.entities.Heart;
import org.team12.model.entities.Item;
import org.team12.model.entities.Laptop;
import org.team12.model.entities.Player;
import org.team12.states.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerHud {
    private int tileSize;

    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public int commandNumber = -1;

    private BufferedImage openLaptop;

    private InputController inputController;


    public PlayerHud(int tileSize, InputController inputController) {
        arial_40 = new Font("Arial", Font.PLAIN, 40);

        Item laptop = new Laptop();

        openLaptop = UtilityTool.scaleImage(laptop.image, tileSize, tileSize);

        this.tileSize = tileSize;
        this.inputController = inputController;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }


    // Draws the player's maximum amount of hearts and updates the heart images to reflect the player's current life



    public void drawGameState(GameState gameState, Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gameState == GameState.PAUSE) {
            drawTitleScreen(g2);
        }

    }

    public void drawPlayScreen(Graphics2D g2) {
        // display messages when items or events happen
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(20f));
            g2.drawString(message, tileSize/2, tileSize*5);
            messageTimer++;

            // Displays the message for 2 seconds
            if (messageTimer > 120){
                messageTimer = 0;
                messageOn = false;
            }
        }

    }

    public void drawTitleScreen(Graphics2D g2) {
        g2.setColor(new Color (10,10,50));
        g2.fillRect(0,0, GameUI.getScreenWidth(), GameUI.getScreenHeight());


        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50f));
        String text = "CSCI 205 Simulator";
        int x = centerText(text, g2);
        int y = tileSize*3;

        // Shadow Text
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Laptop Image
        x=GameUI.getScreenWidth()/2 - (tileSize*2)/2;
        y+=tileSize*2;
        g2.drawImage(openLaptop, x, y, tileSize *2, tileSize*2, null);

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));

        text = "Play Game";
        x = centerText(text, g2);
        y += tileSize * 4;
        g2.drawString(text, x, y);
        if(commandNumber == -1) {
            g2.drawString(">", x - tileSize, y);
        }

        text = "Quit";
        x = centerText(text, g2);
        y += tileSize;
        g2.drawString(text, x, y);
        if(commandNumber == 1) {
            g2.drawString(">", x - tileSize, y);
        }
    }



    public void drawPauseScreen(Graphics2D g2){
        String text = "PAUSED";
        int x = centerText(text, g2);
        int y = GameUI.getScreenHeight()/2;
        g2.drawString (text, x,y);
    }

    public int centerText(String text, Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return GameUI.getScreenWidth()/2 -length/2;
    }

    // YOU CURED LILY, SHE GAVE YOU AN A+

    public GameState checkUserInteraction() {
        if (inputController.downJustPressed | inputController.upJustPressed) {
            // Toggle
            commandNumber = commandNumber*(-1);
            System.out.println("commandNumber: " + commandNumber);

            inputController.resetJustPressed();
        }
        if (inputController.enterKeyJustPressed & (commandNumber == -1)) {
            inputController.resetJustPressed();
            return GameState.PLAYING;
        } else if (inputController.enterKeyJustPressed & (commandNumber == 1)) {
            inputController.resetJustPressed();
            return GameState.END;
        }
        return null;
    }
}


