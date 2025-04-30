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
import org.team12.model.entities.Entity;
import org.team12.view.GameUI;

public class CollisionController {

    private Map map;

    public CollisionController(Map map) {
        this.map = map;
    }

    public boolean canMoveTo(Entity e, int dx, int dy) {
        int futureLeft   = e.worldX + e.hitbox.x + dx;
        int futureTop    = e.worldY + e.hitbox.y + dy;
        int futureRight  = futureLeft + e.hitbox.width;
        int futureBottom = futureTop + e.hitbox.height;

        int leftTile   = futureLeft / GameUI.getTileSize();
        int rightTile  = (futureRight - 1) / GameUI.getTileSize();
        int topTile    = futureTop / GameUI.getTileSize();
        int bottomTile = (futureBottom - 1) / GameUI.getTileSize();

        for (int x = leftTile; x <= rightTile; x++) {
            for (int y = topTile; y <= bottomTile; y++) {
                if (!map.isInsideBounds(x, y)) return false;
                Tile tile = map.getTile(x, y);
                if (tile != null && tile.hasObstacle()) {
                    return false;
                }
            }
        }


            return true;

    }
}
