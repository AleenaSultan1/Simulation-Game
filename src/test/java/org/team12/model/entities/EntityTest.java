package org.team12.model.entities;

import org.junit.jupiter.api.Test;
import org.team12.controller.CollisionController;
import org.team12.model.Map;
import org.team12.states.EnemyStatus;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    @Test
    void testTakeDamageReducesHPAndKillsEnemy() {
        Enemy enemy = new Enemy(5, 5);
        enemy.takeDamage(3);
        assertEquals(2, enemy.getHP());
        enemy.takeDamage(2);
        assertEquals(0, enemy.getHP());
        assertEquals(EnemyStatus.DEAD, enemy.currentEntityStatus);
    }

    @Test
    void testInitialHitboxValues() {
        Enemy enemy = new Enemy(10, 5);
        Rectangle hitbox = enemy.getHitbox();
        assertEquals(8, hitbox.x);
        assertEquals(8, hitbox.y);
        assertEquals(32, hitbox.width);
        assertEquals(32, hitbox.height);
    }

    @Test
    void testGetWorldCoordinates() {
        Enemy enemy = new Enemy(10, 5);
        enemy.worldX = 100;
        enemy.worldY = 200;
        assertEquals(100, enemy.getworldX());
        assertEquals(200, enemy.getworldY());
    }

    @Test
    void testGetMaxHP_ReturnsCorrectValue() {
        Enemy enemy = new Enemy(10, 1);
        assertEquals(10, enemy.getMaxHP());
    }

    @Test
    void testGetCurrentSprite_NotNull() {
        Enemy enemy = new Enemy(10, 1);
        enemy.direction = "down";
        assertNotNull(enemy.getCurrentSprite(), "Sprite should not be null for valid direction");
    }

}
