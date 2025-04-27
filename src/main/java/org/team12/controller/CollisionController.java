/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/24/25
 * Time: 11:24 PM
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

import org.team12.model.Tile;
import org.team12.model.entities.Entity;
import org.team12.model.entities.Item;
import org.team12.view.GameUI;

import java.awt.*;

public class CollisionController {

    GameUI gameUI;
    // Constructor for a collision controller
    public CollisionController(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX / gameUI.tileSize;
        int entityRightCol = entityRightWorldX / gameUI.tileSize;
        int entityTopRow = entityTopWorldY / gameUI.tileSize;
        int entityBottomRow = entityBottomWorldY / gameUI.tileSize;

        Tile tile1, tile2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gameUI.tileSize;
                tile1 = gameUI.map.getTile(entityLeftCol, entityTopRow);
                tile2 = gameUI.map.getTile(entityRightCol, entityTopRow);
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gameUI.tileSize;
                tile1 = gameUI.map.getTile(entityLeftCol, entityBottomRow);
                tile2 = gameUI.map.getTile(entityRightCol, entityBottomRow);
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gameUI.tileSize;
                tile1 = gameUI.map.getTile(entityLeftCol, entityTopRow);
                tile2 = gameUI.map.getTile(entityLeftCol, entityBottomRow);
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gameUI.tileSize;
                tile1 = gameUI.map.getTile(entityRightCol, entityTopRow);
                tile2 = gameUI.map.getTile(entityRightCol, entityBottomRow);
                break;
            default:
                tile1 = null;
                tile2 = null;
                break;
        }

        if ((tile1 != null && tile1.hasObstacle()) || (tile2 != null && tile2.hasObstacle())) {
            entity.collisionOn = true;
        }
    }


    /**
     * Check if the player is hitting any object. Returns the index of the object to process the reaction accordingly
     * @param entity Enemy or Player object
     * @param player returns true if the entity is Player
     * @return index for appropriate reaction
     */
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        // Prepare future hitbox
        Rectangle entityFutureHitbox = new Rectangle(
                entity.worldX + entity.hitbox.x,
                entity.worldY + entity.hitbox.y,
                entity.hitbox.width,
                entity.hitbox.height
        );

        // Move the hitbox according to the movement direction
        switch (entity.direction) {
            case "up":
                entityFutureHitbox.y -= entity.speed;
                break;
            case "down":
                entityFutureHitbox.y += entity.speed;
                break;
            case "left":
                entityFutureHitbox.x -= entity.speed;
                break;
            case "right":
                entityFutureHitbox.x += entity.speed;
                break;
        }

        // Check surrounding tiles (up to 2 tiles touched by entity)
        int entityLeftCol = entityFutureHitbox.x / gameUI.tileSize;
        int entityRightCol = (entityFutureHitbox.x + entityFutureHitbox.width) / gameUI.tileSize;
        int entityTopRow = entityFutureHitbox.y / gameUI.tileSize;
        int entityBottomRow = (entityFutureHitbox.y + entityFutureHitbox.height) / gameUI.tileSize;

        Tile tile1 = gameUI.map.getTile(entityLeftCol, entityTopRow);
        Tile tile2 = gameUI.map.getTile(entityRightCol, entityTopRow);
        Tile tile3 = gameUI.map.getTile(entityLeftCol, entityBottomRow);
        Tile tile4 = gameUI.map.getTile(entityRightCol, entityBottomRow);

        Tile[] tilesToCheck = {tile1, tile2, tile3, tile4};

        for (Tile tile : tilesToCheck) {
            if (tile != null && tile.getItem() != null) {
                Item item = tile.getItem();

                // Build item's full world hitbox
                Rectangle itemHitbox = new Rectangle(
                        item.worldX + item.hitbox.x,
                        item.worldY + item.hitbox.y,
                        item.hitbox.width,
                        item.hitbox.height
                );

                // Check intersection between player's future hitbox and item hitbox
                if (entityFutureHitbox.intersects(itemHitbox)) {
                    if (item.isCollision()) {
                        entity.collisionOn = true;
                    }
                    if (player) {
                        index = 1; // signal that player is touching an item
                    }
                }
            }
        }

        return index;
    }
}



