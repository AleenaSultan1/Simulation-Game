/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:49 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: CollisionControllerTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.team12.controller.CollisionController;
import org.team12.model.Map;
import org.team12.model.entities.Player;
import org.team12.controller.InputController;

public class CollisionControllerTest {

    private Map map;
    private CollisionController collisionController;
    private Player player;

    @BeforeEach
    public void setUp() {
        map = new Map("/map/dungeonMap.txt", false);
        collisionController = new CollisionController(map);
        player = new Player(new InputController(), collisionController, 6);
        map.setPlayer(player);
        map.setCollisionController(collisionController);
    }

    @Test
    public void testCanMoveToEmptyTile() {
        player.worldX = 64; // Set to an open tile
        player.worldY = 64;
        assertTrue(collisionController.canMoveTo(player, 0, 0));
    }

    @Test
    public void testCannotMoveToWallTile() {
        player.worldX = 0; // (0, 0) is a wall in most dungeon maps
        player.worldY = 0;
        assertFalse(collisionController.canMoveTo(player, 0, 0));
    }

    @Test
    public void testNoCollisionWithSelf() {
        assertNull(collisionController.checkEntityCollision(player, 0, 0));
    }
}

