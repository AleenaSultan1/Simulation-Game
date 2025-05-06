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
 * The main player character in the game. Handles movement, inventory management,
 * sprite rendering, combat interactions, and pickup logic. Inherits from Entity.
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
    private int hitboxWidth = 32;
    private int hitboxHeight = 32;
    private CollisionController collisionController;
    private int attackRangeScale;
    private Rectangle attackRange;
    private boolean hasSword;

    // Attack sprite frames
    private BufferedImage attack_d_1, attack_d_2, attack_r_1, attack_r_2;
    private BufferedImage attack_l_1, attack_l_2, attack_u_1, attack_u_2;

    /**
     * Constructs the player with references to input and collision controllers.
     * @param inputController Handles keyboard input
     * @param collisionController Handles movement collisions
     * @param hp Starting health of the player
     */
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

    /**
     * @return Attack range centered around the player's current position
     */
    public Rectangle getAttackRange() {
        int rangeWidth = GameUI.getTileSize() * attackRangeScale;
        int rangeHeight = GameUI.getTileSize() * attackRangeScale;

        int centerX = worldX + (GameUI.getTileSize() / 2);
        int centerY = worldY + (GameUI.getTileSize() / 2);

        int rangeX = centerX - (rangeWidth / 2);
        int rangeY = centerY - (rangeHeight / 2);

        return new Rectangle(rangeX, rangeY, rangeWidth, rangeHeight);
    }

    public int getAttackRangeScale() {
        return attackRangeScale;
    }

    /**
     * Sets player's default starting position, speed, and direction
     */
    public void setDefaultValues() {
        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "down";
    }

    /**
     * Updates player position and animation each frame based on input
     */
    public void update() {
        int dx = 0;
        int dy = 0;

        if (inputController.upPressed || inputController.downPressed ||
                inputController.leftPressed || inputController.rightPressed) {

            if (inputController.upPressed) {
                direction = "up";
                dy -= speed;
            }
            if (inputController.downPressed) {
                direction = "down";
                dy += speed;
            }
            if (inputController.leftPressed) {
                direction = "left";
                dx -= speed;
            }
            if (inputController.rightPressed) {
                direction = "right";
                dx += speed;
            }

            Entity collided = collisionController.checkEntityCollision(this, dx, dy);
            if (collisionController.canMoveTo(this, dx, dy) & collided == null) {
                worldX += dx;
                worldY += dy;
            }

            // Advance walk animation
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        }
    }

    /**
     * Loads all player sprite images including walking and attack animations
     */
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_down_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_right_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_left_2.png")));

            attack_d_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_down_1.png")));
            attack_d_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_down_2.png")));
            attack_r_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_right_1.png")));
            attack_r_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_right_2.png")));
            attack_l_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_left_1.png")));
            attack_l_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_left_2.png")));
            attack_u_1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_up_1.png")));
            attack_u_2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/player_attack_up_2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns current sprite depending on direction and attack state
     */
    @Override
    public BufferedImage getCurrentSprite() {
        BufferedImage image;
        if (hasSword) {
            image = getBufferedImageFromDir(null, attack_u_1, attack_u_2, attack_d_1, attack_d_2, attack_l_1, attack_l_2, attack_r_1, attack_r_2);
        } else {
            image = getBufferedImageFromDir(null, up1, up2, down1, down2, left1, left2, right1, right2);
        }
        return image;
    }

    private BufferedImage getBufferedImageFromDir(BufferedImage image, BufferedImage up1, BufferedImage up2, BufferedImage down1, BufferedImage down2, BufferedImage left1, BufferedImage left2, BufferedImage right1, BufferedImage right2) {
        switch (direction) {
            case "up": image = (spriteNum == 1) ? up1 : up2; break;
            case "down": image = (spriteNum == 1) ? down1 : down2; break;
            case "left": image = (spriteNum == 1) ? left1 : left2; break;
            case "right": image = (spriteNum == 1) ? right1 : right2; break;
        }
        return image;
    }

    // Position & screen info
    public int getTileX() { return worldX / GameUI.getTileSize(); }
    public int getTileY() { return worldY / GameUI.getTileSize(); }
    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }
    public int getHitboxWidth() { return hitboxWidth; }
    public int getHitboxHeight() { return hitboxHeight; }

    /**
     * Attempts to pick up an item if it is interactable
     * @param item Item to pick up
     * @return true if successfully picked up
     */
    public boolean pickUpItem(Item item) {
        if (item != null && item.getItemState() == ItemState.INTERACTABLE) {
            item.pickUp();
            if (item instanceof Sword) hasSword = true;
            inventory.add(item);
            return true;
        }
        return false;
    }

    /**
     * @return All items currently in player's inventory
     */
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    /**
     * Attacks an enemy if the player has a sword equipped
     * @param enemy Enemy to attack
     * @return true if attack was successful
     */
    public boolean attackEnemy(Enemy enemy) {
        Item sword = playerHasItem(Sword.class);
        if (sword instanceof Sword) {
            enemy.takeDamage(((Sword) sword).strength);
            System.out.println("Player attacked with Sword");
            return true;
        }
        return false;
    }

    /**
     * Helper method to check if inventory contains an item of given class
     * @param targetClass Class to search for
     * @return The first matching item, or null
     */
    private Item playerHasItem(Class<? extends Item> targetClass) {
        for (Item item : inventory) {
            if (targetClass.isInstance(item)) {
                return item;
            }
        }
        return null;
    }
}
