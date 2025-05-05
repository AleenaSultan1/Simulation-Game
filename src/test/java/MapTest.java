/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 1:05 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: MapTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Map;
import org.team12.model.Tile;
import org.team12.model.Items.Item;
import org.team12.model.entities.Enemy;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    Map map;

    @BeforeEach
    public void setUp() {
        map = new Map("/map/dungeonMap.txt", false);
    }

    @Test
    public void testMapLoadsTiles() {
        assertNotNull(map.getTile(0, 0));
        assertTrue(map.getWidth() > 0);
        assertTrue(map.getHeight() > 0);
    }

    @Test
    public void testEnemiesLoaded() {
        assertFalse(map.getEnemiesOnMap().isEmpty());
    }

    @Test
    public void testItemsLoaded() {
        assertFalse(map.getItemsOnMap().isEmpty());
    }

    @Test
    public void testInsideBounds() {
        assertTrue(map.isInsideBounds(0, 0));
        assertFalse(map.isInsideBounds(-1, -1));
    }

    @Test
    public void testAddRemoveItem() {
        Item item = map.getItemsOnMap().get(0);
        map.removeItem(item);
        assertFalse(map.getItemsOnMap().contains(item));
    }

    @Test
    public void testRemoveEnemy() {
        Enemy enemy = map.getEnemiesOnMap().get(0);
        map.removeEnemy(enemy);
        assertFalse(map.getEnemiesOnMap().contains(enemy));
    }
}

