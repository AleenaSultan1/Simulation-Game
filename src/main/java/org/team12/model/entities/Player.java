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
import org.team12.controller.UtilityTool;
import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Entity {
    InputController inputController;
    public final int screenX;
    public final int screenY;
    public boolean hasSword = false;
    int sword = 0;
    public boolean canCure = false;


    public Player(GameUI gameUI, InputController inputController) {
        super(gameUI);
        this.gameUI = gameUI;
        this.inputController = inputController;
        // sets the player's position to the halfway point of the screen
        // offset it to account for positioning error
        screenX=gameUI.screenWidth/2 - (gameUI.tileSize/2);
        screenY=gameUI.screenHeight/2 -(gameUI.tileSize/2);

        // Get images for player and reset standard player stats
        this.setDefaultValues();
        this.getPlayerImage();

        // Hit box parameters
        this.hitbox = new Rectangle();
        // Make a smaller box than the sprite
        hitbox.x = 8;
        hitbox.y = 16;
        hitboxDefaultX = hitbox.x;
        hitboxDefaultY = hitbox.y;
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

        // Set characters stats
        maxLife = 6;
        // Current Life
        life = maxLife;
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

            // CHECK TILE COLLISION
            collisionOn = false;
            // check if the player is touching an impassable tile
            gameUI.cController.checkTile(this);
            // check if the player is touching an interactable item
            int objIndex = gameUI.cController.checkObject(this, true);
            pickUpObject(objIndex);

            // if collision is false, player can move
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

    // Scales the player sprites to x3 their original size
    public BufferedImage setup(String imageName){
        UtilityTool utilTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            image = utilTool.scaleImage(image, gameUI.tileSize, gameUI.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

    // Picks up an object if the player is touching it
    public void pickUpObject(int objIndex){

        if (objIndex!=999){
            String objectName = gameUI.obj[objIndex].name;

            switch (objectName){
                case "Sword":
                    this.hasSword = true;
                    gameUI.obj[objIndex] = null;
                    System.out.println("Sword picked up");
            }
            // deletes the object we touched
            //gameUI.obj[objIndex] = null;
        }
    }

    // Gets the player's sprite from resources
    public void getPlayerImage(){
        up1 = setup("player_up_1");
        up2 = setup("player_up_2");
        down1 = setup("player_down_1");
        down2 = setup("player_down_2");
        right1 = setup("player_right_1");
        right2 = setup("player_right_2");
        left1 = setup("player_left_1");
        left2 = setup("player_left_2");

    }

    public void draw(Graphics2D g2) {

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