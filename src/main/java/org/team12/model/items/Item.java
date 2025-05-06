/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:35 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities
 * Class: Item
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import org.team12.states.ItemState;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.team12.view.GameUI;

/**
 * Abstract base class for all items in the game.
 * Defines shared properties such as position, collision state, sprite image,
 * and interaction behavior (e.g., being picked up).
 */
public abstract class Item {
    // Item image
    protected BufferedImage image, image2;

    public String name;
    public boolean collision = false;
    private int worldX;
    private int worldY;

    // The rectangular hitbox used for collision detection.
    public Rectangle hitbox = new Rectangle(worldX, worldY, GameUI.getTileSize(), GameUI.getTileSize());

    // The current state of the item (e.g., interactable, uninteractable).
    public ItemState itemState;

    /**
     * Constructs a new Item with a default state of INTERACTABLE.
     */
    public Item() {
        this.itemState = ItemState.INTERACTABLE;
    }

    /**
     * Sets the X coordinate of the item in tile units.
     *
     * @param x the X coordinate in tile units
     */
    public void setX(int x) {
         this.worldX = x * GameUI.getTileSize();
    }

    /**
     * Sets the Y coordinate of the item in tile units.
     *
     * @param y the Y coordinate in tile units
     */
    public void setY(int y) {
         this.worldY = y * GameUI.getTileSize();
    }

    /**
     * Gets the X coordinate of the item in world (pixel) units.
     *
     * @return the X position in pixels
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * Gets the Y coordinate of the item in world (pixel) units.
     *
     * @return the Y position in pixels
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * Sets the item state to UNINTERACTABLE, representing the item being picked up.
     */
    public void pickUp() {
        itemState = ItemState.UNINTERACTABLE;
    }

    /**
     * Gets the name of the item.
     *
     * @return the item name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the current state of the item.
     *
     * @return the {@link ItemState} of this item
     */
    public ItemState getItemState() {
        return itemState;
    }

    /**
     * Gets the hitbox used for collision detection.
     *
     * @return a {@link Rectangle} representing the hitbox
     */
    public Rectangle getHitbox() {
        return hitbox;
    }

    /**
     * Abstract method to get the sprite image representing the item.
     * Must be implemented by concrete subclasses.
     *
     * @return a {@link BufferedImage} of the item sprite
     */
    public abstract BufferedImage getSprite();
}