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
import org.team12.controller.GameController;

public abstract class Item {
    // Available images
    public BufferedImage image, image2, image3;
    // Used to determine interactions
    public String name;

    public boolean collision = false;

    public int worldX, worldY;


    // All items have a hitbox the size of a tile
    public Rectangle hitbox = new Rectangle(0,0,48,48);
    public int hitboxDefaultX = 0;
    public int hitboxDefaultY = 0;


    // Used to scale images on the GUI
    UtilityTool utilTool = new UtilityTool();



    public ItemState itemState;
    public double interactDistance;
    public double playerDistance;

    // Used to draw the correct sprite for the item
    public void draw (Graphics2D g2, GameController gameController) {

        // This shows where on the screen do we draw
        int screenX = worldX - gameController.player.worldX + gameController.player.screenX;
        int screenY = worldY - gameController.player.worldY + gameController.player.screenY;

        // Rendering Efficiency: Render only the visible parts on the screen
        if (worldX + gameController.tileSize > gameController.player.worldX - gameController.player.screenX &&
                worldX - gameController.tileSize < gameController.player.worldX + gameController.player.screenX &&
                worldY + gameController.tileSize > gameController.player.worldY - gameController.player.screenY &&
                worldY - gameController.tileSize < gameController.player.worldY + gameController.player.screenY){
            // draw the appropriate sprite for the tile
            g2 .drawImage(image, screenX, screenY, gameController.tileSize, gameController.tileSize, null);
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
