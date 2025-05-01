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

import org.team12.controller.CollisionController;
import org.team12.controller.InputController;
import org.team12.states.ItemState;
import org.team12.view.GameUI;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Player extends Entity {
    private ArrayList<Item> inventory;
    InputController inputController;
    private final int screenX;
    private final int screenY;
    private int hitboxWidth = 32;  // usually tile size
    private int hitboxHeight = 32;
    private CollisionController collisionController;

    public Player(InputController inputController, CollisionController collisionController, int hp) {
        super(hp);
        this.inventory = new ArrayList<>();
        this.inputController = inputController;
        this.collisionController = collisionController;
        this.setDefaultValues();
        this.getPlayerImage();
        screenX = GameUI.getScreenWidth() / 2 - (GameUI.getTileSize() / 2);
        screenY = GameUI.getScreenHeight() / 2 - (GameUI.getTileSize() / 2);
    }

    public void setDefaultValues() {
        // Set player's default position. Normally the player spawns in the top left at (0, 0). Moves the player more towards the center of the screen
        worldX = 100;
        worldY = 100;
        speed = 4; // moves 4 pixels per frame
        direction = "down";
    }

    public void update() {
        int dx = 0;
        int dy = 0;
        if (inputController.upPressed || inputController.downPressed ||
                inputController.leftPressed || inputController.rightPressed) {
            if (inputController.upPressed) {
                direction = "up";
                dy -= speed;
            } if (inputController.downPressed) {
                direction = "down";
                dy += speed;
            } if (inputController.leftPressed) {
                direction = "left";
                dx -= speed;
            } if (inputController.rightPressed) {
                direction = "right";
                dx += speed;
            }

            Entity collided = collisionController.checkEntityCollision(this, dx, dy);
            if (collisionController.canMoveTo(this, dx, dy) & collided == null) {
                worldX += dx;
                worldY += dy;
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

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png"))); // This one works
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png"))); // This one works
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));

            // Debug check
            if (up1 == null) {
                System.err.println("Failed to load player sprites!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public int getTileX() {
        return worldX / GameUI.getTileSize();
    }

    public int getTileY() {
        return worldY / GameUI.getTileSize();
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getHitboxWidth() {
        return hitboxWidth;
    }

    public int getHitboxHeight() {
        return hitboxHeight;
    }

    public boolean pickUpItem(Item item) {
        if (item != null && item.getItemState() == ItemState.INTERACTABLE && inputController.interactionKeyPressed) {
            item.pickUp();
            inventory.add(item);

            return true;
        }
        return false;

    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }
}