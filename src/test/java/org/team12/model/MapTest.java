package org.team12.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.controller.CollisionController;
import org.team12.model.entities.Enemy;
import org.team12.model.items.Item;
import org.team12.model.entities.Player;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private Map map;

    @BeforeEach
    void setUp() {
        map = new Map("/map/dungeonMap.txt", false);
    }

    @Test
    void testMapLoadsWithExpectedDimensions() {
        assertTrue(map.getWidth() > 0);
        assertTrue(map.getHeight() > 0);
    }

    @Test
    void testGetTileWithinBounds() {
        assertNotNull(map.getTile(0, 0));
    }

    @Test
    void testGetTileOutOfBounds() {
        assertNull(map.getTile(-1, -1));
        assertNull(map.getTile(map.getWidth(), map.getHeight()));
    }

    @Test
    void testIsInsideBounds() {
        assertTrue(map.isInsideBounds(0, 0));
        assertFalse(map.isInsideBounds(-1, -1));
        assertFalse(map.isInsideBounds(map.getWidth(), map.getHeight()));
    }

    @Test
    void testEnemiesAndItemsLoaded() {
        List<Enemy> enemies = map.getEnemiesOnMap();
        List<Item> items = map.getItemsOnMap();
        assertNotNull(enemies);
        assertNotNull(items);
    }

    @Test
    void testAddAndRemoveItem() {
        DummyItem dummy = new DummyItem();
        map.addItem(dummy, 1, 1);
        assertTrue(map.getItemsOnMap().contains(dummy));

        map.removeItem(dummy);
        assertFalse(map.getItemsOnMap().contains(dummy));
    }

    @Test
    void testRemoveEnemy() {
        List<Enemy> enemies = map.getEnemiesOnMap();
        if (!enemies.isEmpty()) {
            Enemy e = enemies.get(0);
            map.removeEnemy(e);
            assertFalse(map.getEnemiesOnMap().contains(e));
        }
    }

    @Test
    void testSetCollisionControllerPropagates() {
        Map map = new Map("/map/dungeonMap.txt", false);
        CollisionController controller = new CollisionController(map);
        map.setCollisionController(controller);
        List<Enemy> enemies = map.getEnemiesOnMap();
        assertNotNull(enemies);
        assertFalse(enemies.isEmpty());
    }


    @Test
    void testGetEntitiesIncludesPlayer() {
        Player player = new Player(null, null, 10);
        map.setPlayer(player);
        assertTrue(map.getEntitiesOnMap().contains(player));
    }

    // Dummy item for testing
    static class DummyItem extends Item {
        @Override
        public java.awt.image.BufferedImage getSprite() {
            return null;
        }
    }
}
