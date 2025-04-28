/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/28/25
 * Time: 1:25 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: CollisionController
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.team12.view.GameUI;
import org.team12.model.Tile;
import org.team12.model.entities.Entity;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Item;

import java.awt.Rectangle;

public class CollisionController {
    private final GameUI gameUI;

    public CollisionController(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    /**
     * Checks if moving entity will hit an obstacle tile. Sets collisionOn if so.
     */
    public void checkTile(Entity e) {
        // Reset collision flag
        e.collisionOn = false;

        // Current hitbox
        Rectangle hb = new Rectangle(
                e.worldX + e.hitbox.x,
                e.worldY + e.hitbox.y,
                e.hitbox.width,
                e.hitbox.height
        );
        // Future hitbox
        Rectangle futureHB = new Rectangle(hb);
        switch (e.direction) {
            case "up":    futureHB.y -= e.speed; break;
            case "down":  futureHB.y += e.speed; break;
            case "left":  futureHB.x -= e.speed; break;
            case "right": futureHB.x += e.speed; break;
        }

        // Determine tile indices
        int leftCol   = futureHB.x / GameUI.tileSize;
        int rightCol  = (futureHB.x + futureHB.width - 1) / GameUI.tileSize;
        int topRow    = futureHB.y / GameUI.tileSize;
        int bottomRow = (futureHB.y + futureHB.height - 1) / GameUI.tileSize;

        // Check each corner
        Tile t1 = gameUI.map.getTile(leftCol,  topRow);
        Tile t2 = gameUI.map.getTile(rightCol, topRow);
        Tile t3 = gameUI.map.getTile(leftCol,  bottomRow);
        Tile t4 = gameUI.map.getTile(rightCol, bottomRow);

        if ((t1 != null && t1.hasObstacle()) ||
                (t2 != null && t2.hasObstacle()) ||
                (t3 != null && t3.hasObstacle()) ||
                (t4 != null && t4.hasObstacle())) {
            e.collisionOn = true;
        }
    }

    /**
     * Checks collision between entity and any enemy. Returns collided enemy or null.
     */
    public Enemy checkEnemy(Entity e) {
        // Reset flag
        e.collisionOn = false;

        Rectangle hb = new Rectangle(
                e.worldX + e.hitbox.x,
                e.worldY + e.hitbox.y,
                e.hitbox.width,
                e.hitbox.height
        );
        Rectangle futureHB = new Rectangle(hb);
        switch (e.direction) {
            case "up":    futureHB.y -= e.speed; break;
            case "down":  futureHB.y += e.speed; break;
            case "left":  futureHB.x -= e.speed; break;
            case "right": futureHB.x += e.speed; break;
        }
        for (Enemy en : gameUI.map.enemiesOnMap) {
            Rectangle ehb = new Rectangle(
                    en.worldX + en.hitbox.x,
                    en.worldY + en.hitbox.y,
                    en.hitbox.width,
                    en.hitbox.height
            );
            if (futureHB.intersects(ehb)) {
                e.collisionOn = true;
                return en;
            }
        }
        return null;
    }

    /**
     * Checks collision between entity and any item. Returns collided item or null.
     */
    public Item checkObject(Entity e) {
        // Reset flag
        e.collisionOn = false;

        Rectangle hb = new Rectangle(
                e.worldX + e.hitbox.x,
                e.worldY + e.hitbox.y,
                e.hitbox.width,
                e.hitbox.height
        );
        Rectangle futureHB = new Rectangle(hb);
        switch (e.direction) {
            case "up":    futureHB.y -= e.speed; break;
            case "down":  futureHB.y += e.speed; break;
            case "left":  futureHB.x -= e.speed; break;
            case "right": futureHB.x += e.speed; break;
        }
        for (Item it : gameUI.map.itemsOnMap) {
            Rectangle ihb = new Rectangle(
                    it.worldX + it.hitbox.x,
                    it.worldY + it.hitbox.y,
                    it.hitbox.width,
                    it.hitbox.height
            );
            if (futureHB.intersects(ihb)) {
                if (it.isCollision()) {
                    e.collisionOn = true;
                }
                return it;
            }
        }
        return null;
    }

    /**
     * Checks collision between entity and the player. Returns true if collided.
     */
    public boolean checkPlayer(Entity e) {
        // Reset flag
        e.collisionOn = false;

        Rectangle hb = new Rectangle(
                e.worldX + e.hitbox.x,
                e.worldY + e.hitbox.y,
                e.hitbox.width,
                e.hitbox.height
        );
        Rectangle futureHB = new Rectangle(hb);
        switch (e.direction) {
            case "up":    futureHB.y -= e.speed; break;
            case "down":  futureHB.y += e.speed; break;
            case "left":  futureHB.x -= e.speed; break;
            case "right": futureHB.x += e.speed; break;
        }
        Entity p = gameUI.player;
        Rectangle phb = new Rectangle(
                p.worldX + p.hitbox.x,
                p.worldY + p.hitbox.y,
                p.hitbox.width,
                p.hitbox.height
        );
        if (futureHB.intersects(phb)) {
            e.collisionOn = true;
            return true;
        }
        return false;
    }
}
