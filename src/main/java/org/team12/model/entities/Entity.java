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
 * Class: Entity
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.GameController;
import org.team12.controller.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Entity {

    GameController gameController;
    // Global Coordinates for where an entity is in the world
    public int worldX,worldY;
    // How fast an entity moves (4 pixels)
    public int speed;

    // Used for determining direction for animations
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    // Variables to alternate sprites - Creating animations
    public int spriteCounter = 0;
    public int spriteNum =1;

    // Character Stats and Statuses
    public int actionLockCounter = 0;
    public int maxLife;
    public int life;

    // Used for checking collisions/hitboxes
    public Rectangle hitbox;
    public int hitboxDefaultX, hitboxDefaultY;
    public boolean collisionOn = false;

    public BufferedImage image;
    public String name;
    public String type;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // for collision detection later
    public int solidAreaDefaultX, solidAreaDefaultY;


    public Entity(GameController gameController){
        this.gameController = gameController;
    }

    public void setAction() {
    }

    public void update() {
        setAction();
        collisionOn = false;
        gameController.cController.checkTile(this);
        gameController.cController.checkObject(this, false);
        gameController.cController.checkPlayer(this);

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

    public void draw (Graphics2D g2, GameController gameController) {
        BufferedImage image = null;

        // This shows where on the screen do we draw
        int screenX = worldX - gameController.player.worldX + gameController.player.screenX;
        int screenY = worldY - gameController.player.worldY + gameController.player.screenY;

        // Rendering Efficiency: Render only the visible parts on the screen
        if (worldX + gameController.tileSize > gameController.player.worldX - gameController.player.screenX &&
                worldX - gameController.tileSize < gameController.player.worldX + gameController.player.screenX &&
                worldY + gameController.tileSize > gameController.player.worldY - gameController.player.screenY &&
                worldY - gameController.tileSize < gameController.player.worldY + gameController.player.screenY){

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                        break;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                        break;
                    }
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                        break;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                        break;
                    }
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                        break;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                        break;
                    }
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                        break;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                        break;
                    }
                break;
            }
            // draw the appropriate sprite for the tile
            g2 .drawImage(image, screenX, screenY, gameController.tileSize, gameController.tileSize, null);
        }
    }

    public BufferedImage getCurrentSprite() {
        return null;
    }

    // Scales the sprites to x3 their original size
    public BufferedImage setup(String imagePath){
        UtilityTool utilTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilTool.scaleImage(image, gameController.tileSize, gameController.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }

}
