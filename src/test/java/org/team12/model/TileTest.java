package org.team12.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.entities.Enemy;
import org.team12.model.items.Sword;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    private Tile tile;

    @BeforeEach
    void setUp() {
        tile = new Tile(3, 4);
    }

    @Test
    void testSetAndGetItem() {
        Sword sword = new Sword();
        tile.setItem(sword);
        assertEquals(sword, tile.getItem());
    }

    @Test
    void testSetAndGetEnemy() {
        Enemy enemy = new Enemy(10, 2);
        tile.setEnemy(enemy);
        assertEquals(enemy, tile.getEnemy());
    }

    @Test
    void testObstacleFlag() {
        assertFalse(tile.hasObstacle());
        tile.setObstacle(true);
        assertTrue(tile.hasObstacle());
    }

    @Test
    void testHasItem() {
        assertFalse(tile.hasItem());
        tile.setItem(new Sword());
        assertTrue(tile.hasItem());
    }

    @Test
    void testIsOccupiedWhenEmpty() {
        assertTrue(tile.isOccupied());
    }

    @Test
    void testIsOccupiedWithItem() {
        tile.setItem(new Sword());
        assertTrue(tile.isOccupied());
    }

    @Test
    void testIsOccupiedWithEnemy() {
        tile.setEnemy(new Enemy(10, 1));
        assertTrue(tile.isOccupied());
    }

    @Test
    void testIsOccupiedWithObstacle() {
        tile.setObstacle(true);
        assertFalse(tile.isOccupied());
    }
}
