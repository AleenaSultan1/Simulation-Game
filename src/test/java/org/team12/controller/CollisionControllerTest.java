/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:49 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: CollisionControllerTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.team12.model.Map;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Entity;
import org.team12.model.entities.Player;

public class CollisionControllerTest {

    private Map map;
    private CollisionController collisionController;
    private Player player;

    @BeforeEach
    void setUp() {
        map = new Map("/map/dungeonMap.txt", false);
        collisionController = new CollisionController(map);
        player = new Player(new InputController(), collisionController, 5);
        map.setPlayer(player);
    }

    @Test
    void testCanMoveTo_EmptyTile() {
        player.worldX = 48;
        player.worldY = 48;
        assertTrue(collisionController.canMoveTo(player, 0, 0));
    }

    @Test
    void testEntityCollision_DetectsCollision() {
        Enemy enemy1 = new Enemy(5, 1);
        Enemy enemy2 = new Enemy(5, 1);

        // Place both at the same coordinates
        enemy1.worldX = 48;
        enemy1.worldY = 48;

        enemy2.worldX = 48;
        enemy2.worldY = 48;

        map.getEnemiesOnMap().add(enemy1);

        // Check if enemy2 collides with enemy1
        Entity collided = collisionController.checkEntityCollision(enemy2, 0, 0);
        assertNotNull(collided); // Should return enemy1
    }

    @Test
    void testPlayerCollision_TrueWhenOverlapping() {
        Enemy enemy = new Enemy(5, 1);
        enemy.worldX = 48;
        enemy.worldY = 48;

        player.worldX = 48;
        player.worldY = 48;

        assertTrue(collisionController.checkPlayerCollision(enemy, player, 0, 0));
    }

    @Test
    void testSetMap_ChangesReference() {
        Map newMap = new Map("/map/dungeonMap.txt", true);
        collisionController.setMap(newMap);
        assertTrue(collisionController.canMoveTo(player, 0, 0));
    }
}

