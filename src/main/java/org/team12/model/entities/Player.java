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
import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    GameUI gameUI;
    InputController inputController;
    public final int screenX;
    public final int screenY;

    public Player(GameUI gameUI, InputController inputController) {
        this.gameUI = gameUI;
        this.inputController = inputController;
        // sets the player's position to the halfway point of the screen
        // offset it to account for positioning error
        screenX=gameUI.screenWidth/2 - (gameUI.tileSize/2);
        screenY=gameUI.screenHeight/2 -(gameUI.tileSize/2);
        this.setDefaultValues();
        this.getPlayerImage();
        this.hitbox = new Rectangle();
        // Make a smaller box than the sprite
        hitbox.x = 8;
        hitbox.y = 16;
        hitbox.width= 32;
        hitbox.height = 32;

    }

    public void setDefaultValues(){
        // Set player's default position. Normally the player spawns in the top left at (0, 0).
        // Moves the player more towards the center of the screen
        worldX = gameUI.tileSize * 8; //sets the world spawn x coord
        worldY = gameUI.tileSize * 6; // sets the world spawn y coord
        speed = 4; // moves 4 pixels per frame
        direction = "down";
    }

    public void update(){
        if(inputController.upPressed || inputController.downPressed ||
                inputController.leftPressed || inputController.rightPressed) {
            if(inputController.upPressed){
                direction = "up";
            } else if (inputController.downPressed){
                direction = "down";
            } else if (inputController.leftPressed){
                direction = "left";
            } else if (inputController.rightPressed){
                direction = "right";
            }
            collisionOn = false;
            gameUI.cController.checkTile(this);

            // CHECK TILE COLLISION
            if(!collisionOn){
                switch(direction){
                    case "up":
                        worldY-= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            // Used for player walking animation
            spriteCounter ++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }

    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png"))); // This one works
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png"))); // This one works
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));

        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // makes the player a basic square the size of a normal tile
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gameUI.tileSize, gameUI.tileSize);


        // Animation for walking
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                };
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gameUI.tileSize, gameUI.tileSize, null);
    }
}