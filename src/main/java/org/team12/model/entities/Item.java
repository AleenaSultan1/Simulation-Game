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

package org.team12.model.entities;

import org.team12.controller.UtilityTool;
import org.team12.states.ItemState;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.team12.view.GameUI;

public class Item {
    // Item image
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public Rectangle hitbox = new Rectangle(0, 0, 48, 48);
    public int hitboxDefaultX = 0;
    public int hitboxDefaultY = 0;

    UtilityTool utilTool = new UtilityTool();

    public ItemState itemState;
    public double interactDistance;
    public double playerDistance;


    public Item() {
        this.itemState = ItemState.INTERACTABLE;
    }

    public void draw(Graphics2D g2, GameUI gameUI) {
        int screenX = worldX - gameUI.player.worldX + gameUI.player.screenX;
        int screenY = worldY - gameUI.player.worldY + gameUI.player.screenY;

        if (worldX + gameUI.tileSize > gameUI.player.worldX - gameUI.player.screenX &&
                worldX - gameUI.tileSize < gameUI.player.worldX + gameUI.player.screenX &&
                worldY + gameUI.tileSize > gameUI.player.worldY - gameUI.player.screenY &&
                worldY - gameUI.tileSize < gameUI.player.worldY + gameUI.player.screenY) {

            g2.drawImage(image, screenX, screenY, gameUI.tileSize, gameUI.tileSize, null);
        }
    }

    public void pickUp() {
        itemState = ItemState.UNINTERACTABLE;
    }



    public ItemState getItemState() {
        return itemState;
    }
}