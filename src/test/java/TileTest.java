/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 1:06 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: TileTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Tile;
import org.team12.model.Items.Sword;
import org.team12.model.entities.Enemy;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    private Tile tile;

    @BeforeEach
    public void setUp() {
        tile = new Tile(1, 1);
    }

    @Test
    public void testDefaultTileNotObstacle() {
        assertFalse(tile.hasObstacle());
    }

    @Test
    public void testSetAndGetItem() {
        Sword sword = new Sword();
        tile.setItem(sword);
        assertEquals(sword, tile.getItem());
        assertTrue(tile.hasItem());
    }

    @Test
    public void testSetAndGetEnemy() {
        Enemy enemy = new Enemy(10, 2);
        tile.setEnemy(enemy);
        assertEquals(enemy, tile.getEnemy());
    }

    @Test
    public void testSetObstacle() {
        tile.setObstacle(true);
        assertTrue(tile.hasObstacle());
    }

    @Test
    public void testIsOccupied() {
        assertFalse(tile.isOccupied()); // initially not occupied
        tile.setObstacle(true);
        assertFalse(tile.isOccupied()); // still false because `!isObstacle` is false
    }
}

