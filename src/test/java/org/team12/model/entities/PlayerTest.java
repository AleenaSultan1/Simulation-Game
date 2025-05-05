package org.team12.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.controller.CollisionController;
import org.team12.controller.InputController;
import org.team12.model.items.Item;
import org.team12.model.items.Sword;
import org.team12.view.GameUI;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        InputController input = new InputController();
        CollisionController collision = new CollisionController(null);
        player = new Player(input, collision, 100);
    }

    @Test
    void testSetDefaultValues() {
        player.setDefaultValues();
        assertEquals(100, player.worldX);
        assertEquals(100, player.worldY);
        assertEquals(4.0, player.speed);
        assertEquals("down", player.direction);
    }

    @Test
    void testGetAttackRange() {
        Rectangle range = player.getAttackRange();
        assertNotNull(range);
        assertEquals(GameUI.getTileSize() * 3, range.width);
        assertEquals(GameUI.getTileSize() * 3, range.height);
    }

    @Test
    void testGetAttackRangeScale() {
        assertEquals(3, player.getAttackRangeScale());
    }

    @Test
    void testGetInventoryInitiallyEmpty() {
        assertTrue(player.getInventory().isEmpty());
    }

    @Test
    void testPickUpItem_AddsToInventory() {
        Item dummyItem = new Sword();
        assertTrue(player.pickUpItem(dummyItem));
        assertEquals(1, player.getInventory().size());
    }

    @Test
    void testPickUpNullItem() {
        assertFalse(player.pickUpItem(null));
    }

    @Test
    void testAttackEnemy_WithSword() {
        Sword sword = new Sword();
        player.pickUpItem(sword);
        Enemy enemy = new Enemy(20, 3);
        boolean result = player.attackEnemy(enemy);
        assertTrue(result);
        assertTrue(enemy.getHP() < 20);
    }

    @Test
    void testAttackEnemy_WithoutSword() {
        Enemy enemy = new Enemy(20, 3);
        assertFalse(player.attackEnemy(enemy));
        assertEquals(20, enemy.getHP());
    }

    @Test
    void testGetTileXAndY() {
        player.worldX = 64;
        player.worldY = 96;
        assertEquals(1, player.getTileX());
        assertEquals(2, player.getTileY());
    }

    @Test
    void testScreenCoordinates_NotZero() {
        assertTrue(player.getScreenX() > 0);
        assertTrue(player.getScreenY() > 0);
    }

    @Test
    void testHitboxSize() {
        assertEquals(32, player.getHitboxWidth());
        assertEquals(32, player.getHitboxHeight());
    }
}
