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

    public CollisionController(Map map) {
        this.map = map;
    }

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

    // ✅ Collision with other entities
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

    // Collision with the player
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

    // ✅ Helper: get predicted hitbox
    private Rectangle getFutureHitbox(Entity e, int dx, int dy) {
        return new Rectangle(
                e.worldX + e.getHitbox().x + dx,
                e.worldY + e.getHitbox().y + dy,
                e.getHitbox().width,
                e.getHitbox().height
        );
    }

}
