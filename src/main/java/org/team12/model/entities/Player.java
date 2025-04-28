/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:33 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: Player
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.InputController;
import org.team12.model.Tile;
import org.team12.view.GameUI;
import java.awt.image.BufferedImage;



public class Player extends Entity {
    GameUI gameUI;
    InputController inputController;
    public final int screenX;
    public final int screenY;
    public boolean hasSword = false;
    int sword = 0;
    public boolean canCure = false;


    public Player(GameUI gameUI, InputController inputController, int hp) {
        super(hp);
        this.gameUI = gameUI;
        this.inputController = inputController;
        this.setDefaultValues();
        this.getPlayerImage();
        screenX = gameUI.screenWidth / 2 - (gameUI.tileSize / 2);
        screenY = gameUI.screenHeight / 2 - (gameUI.tileSize / 2);

    }

    public void setDefaultValues() {
        // Set player's default position. Normally the player spawns in the top left at (0, 0). Moves the player more towards the center of the screen
        worldX = 100;
        worldY = 100;
        speed = 4; // moves 4 pixels per frame
        direction = "down";
    }

    public void update() {
        if (inputController.upPressed || inputController.downPressed ||
                inputController.leftPressed || inputController.rightPressed) {
            if (inputController.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (inputController.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (inputController.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (inputController.rightPressed) {
                direction = "right";
                worldX += speed;
            }

            // Used for player walking animation
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    //Picks up an object if the player is touching it
    public void pickUpObject(int objIndex) {

        int playerCol = (worldX + hitbox.x) / gameUI.tileSize;
        int playerRow = (worldY + hitbox.y) / gameUI.tileSize;

        Tile currentTile = gameUI.map.getTile(playerCol, playerRow);

        if (currentTile != null && currentTile.getItem() != null) {
            Item item = currentTile.getItem();
            String objectName = item.getName();

            switch (objectName) {
                case "Sword":
                    this.hasSword = true;
                    System.out.println("Sword picked up!");
                    break;
                case "MagicDust":
                    System.out.println("MagicDust picked up!");
                    break;
                // Add more cases for different items
            }

            // Remove the item from the tile after picking it up
            currentTile.setItem(null);
        }
    }

    public void getPlayerImage(){
        up1 = setup("/player/player_up_1");
        up2 = setup("/player/player_up_2");
        down1 = setup("/player/player_down_1");
        down2 = setup("/player/player_down_2");
        right1 = setup("/player/player_right_1");
        right2 = setup("/player/player_right_2");
        left1 = setup("/player/player_left_1");
        left2 = setup("/player/player_left_2");
    }

    @Override
    public BufferedImage getCurrentSprite() {
        // Animation for walking
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                ;
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        return image;
    }
}