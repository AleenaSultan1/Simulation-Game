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

import org.team12.states.ItemState;

import java.awt.*;
import java.awt.image.BufferedImage;
import org.team12.view.GameUI;

public abstract class Item {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;



    public ItemState itemState;
    public double interactDistance;
    public double playerDistance;

    // Used to draw the correct sprite for the item
    public void draw (Graphics2D g2, GameUI gameUI) {

        // This shows where on the screen do we draw
        int screenX = worldX - gameUI.player.worldX + gameUI.player.screenX;
        int screenY = worldY - gameUI.player.worldY + gameUI.player.screenY;

        // Rendering Efficiency: Render only the visible parts on the screen
        if (worldX + gameUI.tileSize > gameUI.player.worldX - gameUI.player.screenX &&
                worldX - gameUI.tileSize < gameUI.player.worldX + gameUI.player.screenX &&
                worldY + gameUI.tileSize > gameUI.player.worldY - gameUI.player.screenY &&
                worldY - gameUI.tileSize < gameUI.player.worldY + gameUI.player.screenY){
            // draw the appropriate sprite for the tile
            g2 .drawImage(image, screenX, screenY, gameUI.tileSize, gameUI.tileSize, null);
        }
    }

}

//
//        protected Item() {
//            this.status = ItemState.INTERACTABLE;
//        }
//
//        public void pickUp() {
//            status = ItemState.UNINTERACTABLE;
//        }
//
//        public ItemState getStatus() {
//            return status;
//        }
//
//    }
//}
