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

package org.team12.model.Items;

import org.team12.view.UtilityTool;
import org.team12.states.ItemState;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.team12.view.GameUI;

public abstract class Item {
    // Item image
    protected BufferedImage image, image2;

    public String name;
    public boolean collision = false;
    private int worldX;
    private int worldY;

    public Rectangle hitbox = new Rectangle(worldX, worldY, GameUI.getTileSize(), GameUI.getTileSize());

    public ItemState itemState;

    public Item() {
        this.itemState = ItemState.INTERACTABLE;
    }

    public void setX(int x) {
         this.worldX = x * GameUI.getTileSize();
    }

    public void setY(int y) {
         this.worldY = y * GameUI.getTileSize();
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }


    public void pickUp() {
        itemState = ItemState.UNINTERACTABLE;
    }

    public String getName() {
        return name;
    }

    public ItemState getItemState() {
        return itemState;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public abstract BufferedImage getSprite();
}