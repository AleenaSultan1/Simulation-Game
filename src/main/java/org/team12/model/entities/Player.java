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

import org.team12.controller.GameController;
import org.team12.controller.InputController;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {
    GameController gameController;
    InputController inputController;
    public final int screenX;
    public final int screenY;
    public boolean hasSword = false;

    // Invincibility frames
    public boolean isInvincible = false;
    public int invincibleCount = 0;

    public boolean canCure = false;


    public Player(GameController gameController, InputController inputController) {
        super(gameController);
        this.gameController = gameController;
        this.inputController = inputController;
        // sets the player's position to the halfway point of the screen
        // offset it to account for positioning error
        screenX= gameController.screenWidth/2 - (gameController.tileSize/2);
        screenY= gameController.screenHeight/2 -(gameController.tileSize/2);

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
        worldX = gameController.tileSize * 18; //sets the world spawn x coord
        worldY = gameController.tileSize * 25; // sets the world spawn y coord
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
            gameController.cController.checkTile(this);

            // Check Item Collision
            int objIndex = gameController.cController.checkObject(this, true);
            pickUpObject(objIndex);

            //Check Enemy Collision
            int monsterIndex = gameController.cController.checkEntity(this, gameController.map.monster);
            contactMonster(monsterIndex);


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

        // Invincibility frames
        if (isInvincible){
            invincibleCount++;
            if (invincibleCount > 60){
                isInvincible = false;
                invincibleCount = 0;
            }
        }


    }
    public void contactMonster(int i){
        if (i!=999){
            if (!isInvincible){
                isInvincible = true;
                life -=1;
            };
        }
    }



    // Picks up an object if the player is touching it
    public void pickUpObject(int objIndex){

        if (objIndex!=999){
            String objectName = gameController.map.obj[objIndex].name;

            switch (objectName){
                case "Sword":
                    this.hasSword = true;
                    gameController.map.obj[objIndex] = null;
                    gameController.pHud.showMessage ("You picked up the sword!");
            }
        }
    }

    // Gets the player's sprite from resources
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
        // Draw invincibility indicator
        if (isInvincible){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY, null);

        // reset the invincibility frames
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}