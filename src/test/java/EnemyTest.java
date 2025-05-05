/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/4/25
 * Time: 10:52 PM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: EnemyTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.team12.controller.CollisionController;
import org.team12.controller.InputController;
import org.team12.model.Map;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Player;
import org.team12.states.EnemyStatus;

public class EnemyTest {

    Enemy enemy;
    Player player;

    @BeforeEach
    public void setUp() {
        InputController input = new InputController();
        Map map = new Map("map/dungeonMap.txt", false);
        CollisionController collisionController = new CollisionController(map);
        map.setCollisionController(collisionController);

        enemy = new Enemy(10, 3);
        enemy.setCollisionController(collisionController);

        player = new Player(input, collisionController, 6);
    }

    @Test
    public void testInitialStateIsPeaceful() {
        assertEquals(EnemyStatus.PEACEFUL, enemy.getState());
    }

    @Test
    public void testSetStateToHostile() {
        enemy.setEnemyState(EnemyStatus.HOSTILE);
        assertEquals(EnemyStatus.HOSTILE, enemy.getState());
    }

    @Test
    public void testSetCoordUpdatesWorldPosition() {
        enemy.setCoord(5, 10);
        int expectedX = 5 * org.team12.view.GameUI.getTileSize();
        int expectedY = 10 * org.team12.view.GameUI.getTileSize();

        assertEquals(expectedX, enemy.worldX);
        assertEquals(expectedY, enemy.worldY);
    }

    @Test
    public void testAttackRangeAndHostilityArea() {
        assertTrue(enemy.getAttackRange() > 0);
        assertTrue(enemy.getHostilityArea() > 0);
    }
}
