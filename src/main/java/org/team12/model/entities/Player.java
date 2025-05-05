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
import org.team12.model.items.Item;
import org.team12.model.items.Laptop;
import org.team12.model.items.Sword;
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
    private int attackRangeScale;
    private Rectangle attackRange;
    private boolean hasSword;
    private BufferedImage attack_d_1, attack_d_2, attack_r_1, attack_r_2, attack_l_1, attack_l_2, attack_u_1, attack_u_2;

    public Player(InputController inputController, CollisionController collisionController, int hp) {
        super(hp);
        hasSword = false;

        this.inventory = new ArrayList<>();
        this.inputController = inputController;
        this.collisionController = collisionController;
        this.setDefaultValues();
        this.getPlayerImage();
        screenX = GameUI.getScreenWidth() / 2 - (GameUI.getTileSize() / 2);
        screenY = GameUI.getScreenHeight() / 2 - (GameUI.getTileSize() / 2);

        attackRangeScale = 3;

        attackRange = new Rectangle();
        attackRange.x = attackRangeScale;
        attackRange.y = attackRangeScale;
        attackRange.width = GameUI.getTileSize() * attackRangeScale;
        attackRange.height = GameUI.getTileSize() * attackRangeScale;
    }

    public Rectangle getAttackRange() {
        int rangeWidth = GameUI.getTileSize() * attackRangeScale;
        int rangeHeight = GameUI.getTileSize() * attackRangeScale;

        // Center the attack range around the player's world position
        int centerX = worldX + (GameUI.getTileSize() / 2);
        int centerY = worldY + (GameUI.getTileSize() / 2);

        int rangeX = centerX - (rangeWidth / 2);
        int rangeY = centerY - (rangeHeight / 2);

        return new Rectangle(rangeX, rangeY, rangeWidth, rangeHeight);
    }

    public int getAttackRangeScale() {
        return attackRangeScale;
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

            attack_d_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_down_1.png")));
            attack_d_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_down_2.png")));
            attack_r_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_right_1.png")));
            attack_r_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_right_2.png")));
            attack_l_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_left_1.png")));
            attack_l_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_left_2.png")));
            attack_u_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_up_1.png")));
            attack_u_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_up_2.png")));

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
        BufferedImage image = null;
        if (hasSword) {
            image = getBufferedImageFromDir(image, attack_u_1, attack_u_2, attack_d_1, attack_d_2, attack_l_1, attack_l_2, attack_r_1, attack_r_2);
        } else {
            // Animation for walking
            image = getBufferedImageFromDir(image, up1, up2, down1, down2, left1, left2, right1, right2);
        }
        return image;
    }

    private BufferedImage getBufferedImageFromDir(BufferedImage image, BufferedImage attackU1, BufferedImage attackU2, BufferedImage attackD1, BufferedImage attackD2, BufferedImage attackL1, BufferedImage attackL2, BufferedImage attackR1, BufferedImage attackR2) {
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = attackU1;
                }
                if (spriteNum == 2) {
                    image = attackU2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = attackD1;
                }
                if (spriteNum == 2) {
                    image = attackD2;
                }
                ;
                break;
            case "left":
                if (spriteNum == 1) {
                    image = attackL1;
                }
                if (spriteNum == 2) {
                    image = attackL2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = attackR1;
                }
                if (spriteNum == 2) {
                    image = attackR2;
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
        if (item != null && item.getItemState() == ItemState.INTERACTABLE) {
            item.pickUp();
            if (item instanceof Laptop) {
            } else if (item instanceof Sword) {
                hasSword = true;
                inventory.add(item);
            } else {
                inventory.add(item);
            }
            return true;
        }
        return false;

    }


    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean attackEnemy(Enemy enemy) {
        // If player has a sword
        Item sword = playerHasItem(Sword.class);
        if (sword instanceof Sword) {
            enemy.takeDamage(((Sword) sword).strength);
            System.out.println("Player attacked with Sword");
            return true;
        }
        return false;
    }

    // Helper method, check if inventory has item
    private Item playerHasItem(Class<? extends Item> targetClass) {
        for (Item item : inventory) {
            if (targetClass.isInstance(item)) {
                return item;
            }
        }
        return null;
    }


}