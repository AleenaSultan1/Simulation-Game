package org.team12.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.controller.CollisionController;
import org.team12.model.Map;
import org.team12.states.EnemyStatus;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    private Enemy enemy;
    private Player player;
    private CollisionController collisionController;
    private Map map;

    @BeforeEach
    void setUp() {
        map = new Map("/map/dungeonMap.txt", false);
        collisionController = new CollisionController(map);
        enemy = new Enemy(10, 10);
        enemy.setCollisionController(collisionController);

        player = new Player(null, collisionController, 10);
        map.setPlayer(player);
    }

    @Test
    void testEnemyInitialHPAndDefaultStatus() {
        assertEquals(10, enemy.getHP());
        assertEquals(EnemyStatus.PEACEFUL, enemy.getState());
    }

    @Test
    void testEnemyTakeDamageAndDie() {
        enemy.takeDamage(10);
        assertTrue(enemy.isDead());
    }

    @Test
    void testEnemyMoveToPlayerChangesDirection() {
        enemy.setCoord(5, 5);
        player.worldX = 300;
        player.worldY = 100;

        enemy.actionLockCounter = 1;
        enemy.enemyMoveToPlayer(player);

        assertNotNull(enemy.direction);
        assertTrue(enemy.direction.equals("right") ||
                enemy.direction.equals("left") ||
                enemy.direction.equals("up") ||
                enemy.direction.equals("down"));
    }

    @Test
    void testEnemyAttackPlayerLowersHP() {
        int initialHP = player.getHP();
        for (int i = 0; i < 60; i++) {
            enemy.enemyAttackPlayer(player); // Only attacks when counter >= 60
        }
        assertTrue(player.getHP() < initialHP);
    }

    @Test
    void testEnemySetCoord() {
        enemy.setCoord(2, 3);
        assertEquals(2 * 48, enemy.worldX);
        assertEquals(3 * 48, enemy.worldY);
    }
}
