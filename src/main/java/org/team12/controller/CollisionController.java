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
 * Description: Handles all collision detection logic for the game, including
 *              tile-based obstacle collisions and entity-to-entity collisions.
 *              Uses predictive hitbox checking to prevent movement through solid
 *              objects and detect interactions between game entities.
 * ****************************************
 */
package org.team12.controller;

import org.team12.model.Map;
import org.team12.model.Tile;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Entity;
import org.team12.model.entities.Player;
import org.team12.view.GameUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CollisionController {

    private Map map;

    /**
     * Constructs a new CollisionController with the specified game map
     * @param map The game map containing tiles and entities to check against
     */
    public CollisionController(Map map) {
        this.map = map;
    }

    /**
     * Checks if an entity can move to a new position without colliding with obstacles
     * @param e The entity attempting to move
     * @param dx The proposed x-axis movement amount
     * @param dy The proposed y-axis movement amount
     * @return true if the movement is allowed, false if it would collide with obstacles
     */
    public boolean canMoveTo(Entity e, int dx, int dy) {
        Rectangle futureHitbox = getFutureHitbox(e, dx, dy);

        int leftTile = futureHitbox.x / GameUI.getTileSize();
        int rightTile = (futureHitbox.x + futureHitbox.width - 1) / GameUI.getTileSize();
        int topTile = futureHitbox.y / GameUI.getTileSize();
        int bottomTile = (futureHitbox.y + futureHitbox.height - 1) / GameUI.getTileSize();

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (!map.isInsideBounds(x, y)) return false;
                Tile tile = map.getTile(x, y);
                if (tile != null && tile.hasObstacle()) return false;
            }
        }

        return true;
    }

    /**
     * Checks for collisions between an entity and all other entities in the game world
     * @param e The entity to check collisions for
     * @param dx The proposed x-axis movement amount
     * @param dy The proposed y-axis movement amount
     * @return The first entity that would be collided with, or null if no collision
     */
    public Entity checkEntityCollision(Entity e, int dx, int dy) {
        Rectangle eBox = getFutureHitbox(e, dx, dy);
        List<Entity> others = new ArrayList<>(map.getEntitiesOnMap());
        others.remove(e);
        for (Entity other : others) {
            if (other == e) continue;
            Rectangle oBox = new Rectangle(
                    other.worldX + other.getHitbox().x,
                    other.worldY + other.getHitbox().y,
                    other.getHitbox().width,
                    other.getHitbox().height
            );
            if (eBox.intersects(oBox)) return other;
        }

        return null;
    }

    /**
     * Specialized check for player collisions with another entity
     * @param e The entity that might collide with the player
     * @param player The player entity
     * @param dx The proposed x-axis movement amount
     * @param dy The proposed y-axis movement amount
     * @return true if the entity would collide with the player, false otherwise
     */
    public boolean checkPlayerCollision(Entity e, Player player, int dx, int dy) {
        Rectangle eBox = getFutureHitbox(e, dx, dy);
        Rectangle playerBox = new Rectangle(
                player.worldX + player.getHitbox().x,
                player.worldY + player.getHitbox().y,
                player.getHitbox().width,
                player.getHitbox().height
        );
        return eBox.intersects(playerBox);
    }

    /**
     * Calculates an entity's future hitbox based on proposed movement
     * @param e The entity to calculate for
     * @param dx The proposed x-axis movement amount
     * @param dy The proposed y-axis movement amount
     * @return Rectangle representing the predicted hitbox position
     */
    private Rectangle getFutureHitbox(Entity e, int dx, int dy) {
        return new Rectangle(
                e.worldX + e.getHitbox().x + dx,
                e.worldY + e.getHitbox().y + dy,
                e.getHitbox().width,
                e.getHitbox().height
        );
    }

    /**
     * Updates the map reference for collision detection
     * @param map The new game map to use for collision checks
     */
    public void setMap(Map map) {
        this.map = map;
    }
}